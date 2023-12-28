package com.WorkSphere.WorkSphere.Controllers.Qusetion;

import com.WorkSphere.WorkSphere.DTOs.Question.QuestionDTO;
import com.WorkSphere.WorkSphere.Services.Qusetion.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public void addQuestion(@RequestBody QuestionDTO question,@AuthenticationPrincipal UserDetails userDetails) {
        questionService.addQuestion(question, userDetails);
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<Object> getAllUserQuestions(@PathVariable UUID userId, @RequestParam final long pageNumber) {
        return questionService.getAllUserQuestions(userId, pageNumber);
    }
}
