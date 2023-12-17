package com.WorkSphere.WorkSphere.Services.Performance;

import com.WorkSphere.WorkSphere.DAOs.Performance.Performance;
import com.WorkSphere.WorkSphere.DAOs.Submission.Submission;
import com.WorkSphere.WorkSphere.DTOs.Performance.PerformanceDTOMapper;
import com.WorkSphere.WorkSphere.DTOs.Performance.PerformanceDTO;
import com.WorkSphere.WorkSphere.Repositories.PerformanceRepository;
import com.WorkSphere.WorkSphere.Repositories.SubmissionRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final SubmissionRepository submissionRepository;
    private final PerformanceDTOMapper performanceDTOMapper;
    public PerformanceServiceImpl(PerformanceRepository performanceRepository, SubmissionRepository submissionRepository, PerformanceDTOMapper performanceDTOMapper) {
        this.performanceRepository = performanceRepository;
        this.submissionRepository = submissionRepository;
        this.performanceDTOMapper = performanceDTOMapper;
    }

    @Override
    public PerformanceDTO getUserPerformance(UUID userId) {
        calculateOverAllRating(userId);
        return performanceDTOMapper.apply(performanceRepository.findByUserId(userId));
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber -1, 10);
        List<Performance> performances = performanceRepository.findAllPaged(pageable);
        for (Performance performance : performances){
            calculateOverAllRating(performance.getUserEntity().getId());
        }
        return ResponseHandler.generateResponse(performances.stream().map(performanceDTOMapper).toList(), HttpStatus.OK,performances.size(),performanceRepository.getPerformanceCount(pageable));
    }

    public void calculateOverAllRating(UUID userId){
        List<Submission> submissions = submissionRepository.getAllUserSubmissions(userId);
        double total=0;
        for (Submission submission : submissions){
            total =+ submission.getRating();
        }
        double overAllPerformance = total/submissions.size();
        Performance performance = performanceRepository.findByUserId(userId);
        performance.setOverallRating(overAllPerformance);
    }
}
