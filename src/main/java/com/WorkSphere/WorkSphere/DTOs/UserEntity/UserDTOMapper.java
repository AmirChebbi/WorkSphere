package com.WorkSphere.WorkSphere.DTOs.UserEntity;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<UserEntity, UserDTO> {
    @Override
    public UserDTO apply(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber(),
                userEntity.isEnabled(),
                userEntity.getCreationDate(),
                userEntity.getRating(),
                userEntity.getRole()
        );
    }
}
