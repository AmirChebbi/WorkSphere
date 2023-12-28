package com.WorkSphere.WorkSphere.Services.Task;

import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface TaskService {
    public ResponseEntity<Object> addTask(TaskDTO taskDTO, UserDetails userDetails);
    public ResponseEntity<Object> removeTask(long id);
    public TaskDTO getTaskById(long id);
    public ResponseEntity<Object> getAllUserTasks(UUID userId, long pageNumber);
    public ResponseEntity<Object> getAllNonSubmittedTasks(long pageNumber);
}
