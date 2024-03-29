package com.WorkSphere.WorkSphere.Services.Submission;

import com.WorkSphere.WorkSphere.DAOs.Submission.Submission;
import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionDTO;
import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionDTOMapper;
import com.WorkSphere.WorkSphere.DTOs.Submission.SubmissionPreEval;
import com.WorkSphere.WorkSphere.Exceptions.ResourceNotFoundException;
import com.WorkSphere.WorkSphere.Repositories.SubmissionRepository;
import com.WorkSphere.WorkSphere.Repositories.TaskRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    private final SubmissionRepository submissionRepository;
    private final SubmissionDTOMapper submissionDTOMapper;
    private final TaskRepository taskRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, SubmissionDTOMapper submissionDTOMapper, TaskRepository taskRepository) {
        this.submissionRepository = submissionRepository;
        this.submissionDTOMapper = submissionDTOMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<Object> addSubmission(SubmissionPreEval submission) {
        submissionRepository.save(new Submission(
                submission.id(),
                submission.task(),
                submission.submissionDate()
        ));
        Task task =taskRepository.findById(submission.task().getId()).orElseThrow( () -> new ResourceNotFoundException("We had trouble finding the task"));
        task.setSubmitted(true);
        taskRepository.save(task);
        final String successMessage = String.format("Your Submission was saved successfully");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public SubmissionDTO getSubmissionById(long id) {
        return submissionDTOMapper.apply(submissionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This submission doesn't exist!")));
    }

    @Override
    public ResponseEntity<Object> evaluateSubmission(long id, double note) {
        Submission submission = submissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This submission doesn't exist !!"));
        if (note<=10 && note >= 0){
            submission.setRating(note);
            submissionRepository.save(submission);
            String successMessage = String.format("Evaluation Done Successfully !");
            return ResponseHandler.generateResponse(successMessage,HttpStatus.OK);
        } else {
            String failureMessage = String.format("Evaluation out the interval");
            return ResponseHandler.generateResponse(failureMessage,HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Object> getSubmissionsByUser(String email, long pageNumber) {
        int adjustedPageNumber = (int) Math.max(pageNumber,1);
        final Pageable pageable = (Pageable) PageRequest.of( adjustedPageNumber -1, 10);
        final List<SubmissionDTO> submissionDTOS = submissionRepository.getSubmissionsByUserId(email, pageable).stream().map(submissionDTOMapper).toList();
        if (submissionDTOS.isEmpty() && pageNumber<1){
            getSubmissionsByUser(email, 1);
        }
        return ResponseHandler.generateResponse(submissionDTOS, HttpStatus.OK, submissionDTOS.size(), submissionRepository.getNumberSubmissionsByUserId(email));
    }
}
