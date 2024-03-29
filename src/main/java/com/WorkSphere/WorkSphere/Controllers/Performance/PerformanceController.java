package com.WorkSphere.WorkSphere.Controllers.Performance;

import com.WorkSphere.WorkSphere.DTOs.Performance.PerformanceDTO;
import com.WorkSphere.WorkSphere.Services.Performance.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/getByUserEmail")
    public PerformanceDTO getUserPerformance(@RequestParam String email){
        return performanceService.getUserPerformance(email);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestParam long pageNumber){
        return performanceService.getAll(pageNumber);
    }
}
