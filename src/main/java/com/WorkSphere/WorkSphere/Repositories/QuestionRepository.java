package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.userEntity.email = :email order by q.id")
    List<Question> fetchAllUserQuesions(@Param("Email")String email, Pageable pageable);

    @Query("select COUNT (q) from Question q where q.userEntity.email = :email")
    long getUserQuestionsNumber(@Param("Email")String email);
}
