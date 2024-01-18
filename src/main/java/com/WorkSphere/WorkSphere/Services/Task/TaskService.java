package com.WorkSphere.WorkSphere.Services.Task;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    public ResponseEntity<Object> addTask(Task task);
    public ResponseEntity<Object> removeTask(long id);
    public TaskDTO getTaskById(long id);
    public ResponseEntity<Object> getAllUserTasks(String email, long pageNumber);
    public ResponseEntity<Object> getAllNonSubmittedTasks(long pageNumber);
}
