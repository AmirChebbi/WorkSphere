package com.WorkSphere.WorkSphere.Services.Task;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTOMapper;
import com.WorkSphere.WorkSphere.Exceptions.ResourceNotFoundException;
import com.WorkSphere.WorkSphere.Repositories.TaskRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final TaskDTOMapper taskDTOMapper;


    public TaskServiceImpl(TaskRepository taskRepository, TaskDTOMapper taskDTOMapper) {
        this.taskRepository = taskRepository;
        this.taskDTOMapper = taskDTOMapper;
    }

    @Override
    public ResponseEntity<Object> addTask(Task task) {
        taskRepository.save(task);
        final String successResponse = String.format("This task has been added successfully !");
        return ResponseHandler.generateResponse(successResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeTask(long id) {
        taskRepository.deleteById(id);
        final String successMessage = String.format("Ths task has been removed successfully !");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public TaskDTO getTaskById(long id) {
        return taskDTOMapper.apply(taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This task doesn't exist in our system")));
    }

    @Override
    public ResponseEntity<Object> getAllUserTasks(UUID userId, long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber -1, 10);
        final List<TaskDTO> taskDTOS = taskRepository.findAllUserTasks(userId,pageable).stream().map(taskDTOMapper).toList();
        if (taskDTOS.isEmpty() && pageNumber < 1){
            getAllUserTasks(userId, 1);
        }
        return ResponseHandler.generateResponse(taskDTOS,HttpStatus.OK, taskDTOS.size(), taskRepository.getAllUserTasksCount(userId));
    }

    @Override
    public ResponseEntity<Object> getAllNonSubmittedTasks(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber -1 , 10);
        final List<TaskDTO> taskDTOS = taskRepository.findAllNonSubmittedTasks(pageable);
        if (taskDTOS.isEmpty() && pageNumber<1){
            getAllNonSubmittedTasks(1);
        }
        return ResponseHandler.generateResponse(taskDTOS, HttpStatus.OK, taskDTOS.size(), taskRepository.getAllNonSubmittedTasksCount());
    }
}
