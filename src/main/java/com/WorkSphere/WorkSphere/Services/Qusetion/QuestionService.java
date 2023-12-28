package com.WorkSphere.WorkSphere.Services.Qusetion;


import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import com.WorkSphere.WorkSphere.DTOs.Question.QuestionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface QuestionService {
    public void addQuestion(QuestionDTO questionDTO, UserDetails userDetails);
    public ResponseEntity<Object> getAllUserQuestions(UUID userId, long pageNumber);
}
