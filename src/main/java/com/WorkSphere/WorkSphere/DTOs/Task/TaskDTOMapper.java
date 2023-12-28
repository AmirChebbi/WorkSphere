package com.WorkSphere.WorkSphere.DTOs.Task;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.Services.UserEntity.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TaskDTOMapper implements Function<Task, TaskDTO> {

    private final UserService userService;

    public TaskDTOMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public TaskDTO apply(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getDoer().getFirstName() + " " + task.getDoer().getLastName(),
                task.getDoer().getEmail(),
                task.getSubmissionDeadline(),
                task.isSubmitted()
        );
    }

    public Task reverse(TaskDTO taskDTO, UserDetails userDetails){
        return  new Task(
                taskDTO.id(),
                taskDTO.name(),
                taskDTO.description(),
                userService.getUserEntityByEmail(userDetails.getUsername()),
                taskDTO.submissionDeadline(),
                taskDTO.isSubmitted()
        );
    }
}
