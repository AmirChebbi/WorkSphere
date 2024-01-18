package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Performance.Performance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;
@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query(value = "SELECT p from Performance p where p.userEntity.id = :userId")
    Performance findByUserId(UUID userId);

    @Query(value = "select p from Performance p")
    List<Performance> findAllPaged(Pageable pageable);

    @Query(value = "select count(p) from Performance p")
    long getPerformanceCount();
}
