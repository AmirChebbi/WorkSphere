package com.WorkSphere.WorkSphere.DTOs.Performance;

import com.WorkSphere.WorkSphere.DAOs.Performance.Performance;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class PerformanceDTOMapper implements Function<Performance, PerformanceDTO> {

    @Override
    public PerformanceDTO apply(Performance performance) {
        return new PerformanceDTO(
                performance.getId(),
                performance.getOverallRating(),
                performance.getUserEntity().getFirstName() + " " + performance.getUserEntity().getLastName()
        );
    }
}
