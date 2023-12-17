package com.WorkSphere.WorkSphere.Services.Qusetion;


import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface QuestionService {
    public void addQuestion(Question question);
    public ResponseEntity<Object> getAllUserQuestions(UUID userId, long pageNumber);
}
