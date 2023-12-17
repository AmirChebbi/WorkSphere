package com.WorkSphere.WorkSphere.DTOs.Submission;

import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;


import java.util.Date;

public record SubmissionDTO(
        long id,
        TaskDTO task,
        Date submissionDate,
        double rating
) {
}
