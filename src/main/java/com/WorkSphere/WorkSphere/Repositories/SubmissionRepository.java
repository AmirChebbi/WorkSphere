package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Submission.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    @Query(value = "SELECT s from Submission s where s.task.doer.id = :userId")
    List<Submission> getSubmissionsByUserId(@Param("userId") UUID userId, Pageable pageable);
    @Query(value = "SELECT s from Submission s where s.task.doer.id = :userId")
    List<Submission> getAllUserSubmissions(@Param("userId") UUID userId);
    @Query(value = "SELECT count (s) from Submission s where s.task.doer.id = :userId" )
    long getNumberSubmissionsByUserId(@Param("userId") UUID userId);
}
