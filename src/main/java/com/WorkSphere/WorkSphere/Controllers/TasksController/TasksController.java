package com.WorkSphere.WorkSphere.Controllers.TasksController;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;
import com.WorkSphere.WorkSphere.Services.Task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addTask(@RequestBody TaskDTO task, @AuthenticationPrincipal UserDetails userDetails){
        return taskService.addTask(task, userDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeTask(@PathVariable long id){
        return taskService.removeTask(id);
    }

    @GetMapping("/getById/{id}")
    public TaskDTO getTaskById(@PathVariable long id){
        return taskService.getTaskById(id);
    }

    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<Object> getAllUserTasks(@PathVariable UUID userId, @RequestParam long pageNumber){
        return taskService.getAllUserTasks(userId, pageNumber);
    }

    @GetMapping("/getNonSubmitted")
    public ResponseEntity<Object> getAllNonSubmittedTasks(@RequestParam long pageNumber){
        return taskService.getAllNonSubmittedTasks(pageNumber);
    }
}
