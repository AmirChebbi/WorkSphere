package com.WorkSphere.WorkSphere.Controllers.Submission;

import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionDTO;
import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionPreEval;
import com.WorkSphere.WorkSphere.Services.Submission.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/submission")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addSubmission(@RequestBody SubmissionPreEval submission){
        return submissionService.addSubmission(submission);
    }

    @GetMapping("/getById/{id}")
    public SubmissionDTO getSubmissionById(@PathVariable long id){
        return submissionService.getSubmissionById(id);
    }

    @PutMapping("/evaluate/{id}")
    public ResponseEntity<Object> evaluateSubmission(@PathVariable long id, @RequestParam double note){
        return submissionService.evaluateSubmission(id,note);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<Object> getSubmissionsByUser(@RequestParam String email, @RequestParam long pageNumber){
        return submissionService.getSubmissionsByUser(email,pageNumber);
    }
}
