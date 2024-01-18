package com.WorkSphere.WorkSphere.Controllers.TasksController;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;
import com.WorkSphere.WorkSphere.Services.Task.TaskService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeTask(@PathVariable long id){
        return taskService.removeTask(id);
    }

    @GetMapping("/getById/{id}")
    public TaskDTO getTaskById(@PathVariable long id){
        return taskService.getTaskById(id);
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<Object> getAllUserTasks(@RequestParam String email, @RequestParam long pageNumber){
        return taskService.getAllUserTasks(email, pageNumber);
    }

    @GetMapping("/getNonSubmitted")
    public ResponseEntity<Object> getAllNonSubmittedTasks(@RequestParam long pageNumber){
        return taskService.getAllNonSubmittedTasks(pageNumber);
    }
}
