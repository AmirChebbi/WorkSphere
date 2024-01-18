package com.WorkSphere.WorkSphere.Services.Performance;

import com.WorkSphere.WorkSphere.DAOs.Performance.Performance;
import com.WorkSphere.WorkSphere.DAOs.Submission.Submission;
import com.WorkSphere.WorkSphere.DTOs.Performance.PerformanceDTOMapper;
import com.WorkSphere.WorkSphere.DTOs.Performance.PerformanceDTO;
import com.WorkSphere.WorkSphere.Repositories.PerformanceRepository;
import com.WorkSphere.WorkSphere.Repositories.SubmissionRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PerformanceDTO getUserPerformance(String email) {
        calculateOverAllRating(email);
        return performanceDTOMapper.apply(performanceRepository.findByUserEmail(email));
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber -1, 10);
        List<Performance> performances = performanceRepository.findAllPaged(pageable);
        for (Performance performance : performances){
            calculateOverAllRating(performance.getUserEntity().getEmail());
        }
        return ResponseHandler.generateResponse(performances.stream().map(performanceDTOMapper).toList(), HttpStatus.OK,performances.size(),performanceRepository.getPerformanceCount());
    }

    public void calculateOverAllRating(String email){
        List<Submission> submissions = submissionRepository.getAllUserSubmissions(email);
        double total=0;
        for (Submission submission : submissions){
            total =+ submission.getRating();
        }
        double overAllPerformance = total/submissions.size();
        Performance performance = performanceRepository.findByUserEmail(email);
        performance.setOverallRating(overAllPerformance);
    }
}
