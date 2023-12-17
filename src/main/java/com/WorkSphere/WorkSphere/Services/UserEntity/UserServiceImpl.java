package com.WorkSphere.WorkSphere.Services.UserEntity;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import com.WorkSphere.WorkSphere.DTOs.UserEntity.UserDTO;
import com.WorkSphere.WorkSphere.DTOs.UserEntity.UserDTOMapper;
import com.WorkSphere.WorkSphere.Exceptions.ResourceNotFoundException;
import com.WorkSphere.WorkSphere.Repositories.UserRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }


    @Override
    public ResponseEntity<Object> fetchUserById(final UUID userId) {
        final UserEntity user = getUserEntityById(userId);
        final UserDTO userEntityDto = userDTOMapper.apply(user);

        return ResponseHandler.generateResponse(userEntityDto , HttpStatus.OK);
    }


    @Override
    public  ResponseEntity<Object> fetchAllUsers(final long pageNumber)
    {
        final Pageable pageable = PageRequest.of((int) pageNumber - 1, 10);
        final List<UserDTO> userEntityFullDTOList = userRepository.fetchAllUsers(pageable).stream().map(userDTOMapper).toList();

        if(userEntityFullDTOList.isEmpty() && pageNumber > 1)
        {
            return fetchAllUsers(1);
        }

        return ResponseHandler.generateResponse(userEntityFullDTOList,HttpStatus.OK,userEntityFullDTOList.size(),userRepository.getTotalUserCount());

    }
    @Override
    public ResponseEntity<Object> fetchCurrentUser(@NotNull final UserDetails userDetails)
    {
        final UserEntity currentUser = getUserEntityByEmail(userDetails.getUsername());
        final UserDTO currentUserDto = userDTOMapper.apply(currentUser);

        return ResponseHandler.generateResponse(currentUserDto , HttpStatus.OK);

    }

    @Override
    public  ResponseEntity<Object> enableOrDisableUser(@NotNull final UUID userId , final boolean enabled)
    {
        UserEntity currentUser = getUserEntityById(userId);
        currentUser.setEnabled(enabled);
        userRepository.save(currentUser);
        final String successResponse = String.format("The user with email : %s  enabled = %s", currentUser.getEmail(),enabled? "true" :"false");

        return ResponseHandler.generateResponse(successResponse, HttpStatus.OK);
    }
    @Override
    public void enableUserById(final UUID userId)
    {
        UserEntity userEntity = getUserEntityById(userId);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
    }

    @Override
    public boolean isEmailRegistered(final String email)
    {
        return userRepository.isEmailRegistered(email);
    }
    @Override
    public boolean isPhoneNumberRegistered(final String phoneNumber)
    {
        return userRepository.isPhoneNumberRegistered(phoneNumber);
    }
    @Override
    public UserEntity saveUser(@NotNull final UserEntity userEntity)
    {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity addEmployee(@NotNull UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity getUserEntityById(final UUID userId)
    {
        return userRepository.fetchUserWithId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("The user with ID : %s could not be found.", userId)));
    }

    @Override
    public UserEntity getUserEntityByEmail(@NotNull final String userEmail)
    {
        return userRepository.fetchUserWithEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("The user with email : %s could not be found.", userEmail)));
    }

}
