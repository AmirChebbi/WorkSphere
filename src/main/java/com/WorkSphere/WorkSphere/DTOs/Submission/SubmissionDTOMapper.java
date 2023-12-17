package com.WorkSphere.WorkSphere.DTOs.Submission;

import com.WorkSphere.WorkSphere.DAOs.Submission.Submission;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTOMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubmissionDTOMapper implements Function<Submission, SubmissionDTO> {

    private final TaskDTOMapper taskDTOMapper;

    public SubmissionDTOMapper(TaskDTOMapper taskDTOMapper) {
        this.taskDTOMapper = taskDTOMapper;
    }

    @Override
    public SubmissionDTO apply(Submission submission) {
        return new SubmissionDTO(
                submission.getId(),
                taskDTOMapper.apply(submission.getTask()),
                submission.getSubmissionDate(),
                submission.getRating()
        );
    }
}
