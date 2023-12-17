package com.WorkSphere.WorkSphere.DTOs.Performance;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;

public record PerformanceDTO(
        long id,
        double overallRating,
        String userEntity
) {
}
