package com.WorkSphere.WorkSphere.Services.Submission;


import com.WorkSphere.WorkSphere.DAOs.Submission.Submission;
import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionDTO;
import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionPreEval;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface SubmissionService {

    public ResponseEntity<Object> addSubmission(SubmissionPreEval submission);

    public SubmissionDTO getSubmissionById(long id);

    public ResponseEntity<Object> evaluateSubmission(long id, double note);

    public ResponseEntity<Object> getSubmissionsByUser(UUID userId, long pageNumber);



}
