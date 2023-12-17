package com.WorkSphere.WorkSphere.DTOs.Submission;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;

import java.util.Date;

public record SubmissionPreEval (
    long id,
    Task task,
    Date submissionDate
){
}
