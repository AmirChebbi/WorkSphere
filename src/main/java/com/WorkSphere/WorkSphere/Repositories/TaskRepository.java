package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Task.Task;
import com.WorkSphere.WorkSphere.DTOs.Task.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select t from Task t where t.doer.id = : userId")
    List<Task> findAllUserTasks(@Param("userId") UUID userId, Pageable pageable);

    @Query(value = "SELECT count (t) from Task t where t.doer.id = :userId")
    long getAllUserTasksCount(UUID userId);

    @Query(value = "SELECT t from Task t where t.isSubmitted = false ")
    List<TaskDTO> findAllNonSubmittedTasks(Pageable pageable);
    @Query(value = "SELECT count (t) from Task t where t.isSubmitted =  false ")
    long getAllNonSubmittedTasksCount();
}
