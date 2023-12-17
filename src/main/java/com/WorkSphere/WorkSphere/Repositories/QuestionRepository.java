package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.userEntity.id = :userId order by q.id")
    List<Question> fetchAllUserQuesions(@Param("userId")UUID userId, Pageable pageable);

    @Query("select COUNT (q) from Question q where q.userEntity.id = :userId")
    long getUserQuestionsNumber(@Param("userId")UUID userId);
}
