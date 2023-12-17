package com.WorkSphere.WorkSphere.DTOs.Task;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;

import java.util.Date;

public record TaskDTO(
        long id,
        String name,
        String description,
        String doer,
        Date submissionDeadline,
        boolean isSubmitted
) {
}
