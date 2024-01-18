package com.WorkSphere.WorkSphere.Services.Performance;

import com.WorkSphere.WorkSphere.DTOs.Performance.PerformanceDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PerformanceService {
    public PerformanceDTO getUserPerformance(String email);
    public ResponseEntity<Object> getAll(long pageNumber);
}
