package com.WorkSphere.WorkSphere.DTOs.UserEntity;

import com.WorkSphere.WorkSphere.DAOs.Role.Role;

import java.util.Date;
import java.util.UUID;

public record UserDTO (
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        boolean isEnabled,
        Date creationDate,
        Double rating,
        Role role
){
}
