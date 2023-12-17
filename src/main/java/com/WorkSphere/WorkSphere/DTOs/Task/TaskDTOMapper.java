package com.WorkSphere.WorkSphere.DTOs.Task;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TaskDTOMapper implements Function<Task, TaskDTO> {
    @Override
    public TaskDTO apply(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getDoer().getFirstName() + " " + task.getDoer().getLastName(),
                task.getSubmissionDeadline(),
                task.isSubmitted()
        );
    }
}
