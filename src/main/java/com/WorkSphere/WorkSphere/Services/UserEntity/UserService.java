package com.WorkSphere.WorkSphere.Services.UserEntity;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserService {
    public ResponseEntity<Object> fetchUserById(final UUID userId);
    public  ResponseEntity<Object> fetchAllUsers(final long pageNumber);
    public ResponseEntity<Object> fetchCurrentUser(final UserDetails userDetails);
    public ResponseEntity<Object> enableOrDisableUser(@NotNull final UUID userId , final boolean enabled);
    public void enableUserById(final UUID userId);
    public UserEntity getUserEntityById(final UUID userId);
    public UserEntity getUserEntityByEmail(final String email);
    public boolean isEmailRegistered(final String email);
    public boolean isPhoneNumberRegistered(final String phoneNumber);
    public UserEntity saveUser(@NotNull final UserEntity userEntity);
    public UserEntity addEmployee(@NotNull final UserEntity userEntity);

}
