package com.WorkSphere.WorkSphere.DTOs.Submission;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;

import java.util.Date;

public record SubmissionPreEval (
    long id,
    Task task,
    Date submissionDate
){
}
