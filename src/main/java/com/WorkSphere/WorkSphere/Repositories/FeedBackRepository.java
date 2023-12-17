package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
    @Query("SELECT f from FeedBack f where f.sender.id = :id ")
    List<FeedBack> findFeedBackBySender(@Param("id")UUID id);

    @Query("SELECT f from FeedBack f order by f.id")
    List<FeedBack> fetchAll(Pageable pageable);

    @Query(value = "select  count(f) from FeedBack f")
    long getTotalFeedBackCount();

    @Query(value = "select  count(f) from FeedBack f where f.sender.id = :userId")
    long getUserFeedBackCount(@Param("userId")UUID userId);
}
