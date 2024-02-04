package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Performance.Performance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Query(value = "SELECT p from Performance p where p.userEntity.email = :email")
    Performance findByUserEmail(@Param("Email") String email);

    @Query(value = "select p from Performance p")
    List<Performance> findAllPaged(Pageable pageable);

    @Query(value = "select count(p) from Performance p")
    long getPerformanceCount();
}
