package com.WorkSphere.WorkSphere.Controllers.Qusetion;

import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import com.WorkSphere.WorkSphere.Services.Qusetion.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public void addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<Object> getAllUserQuestions(@RequestParam String email, @RequestParam final long pageNumber) {
        return questionService.getAllUserQuestions(email, pageNumber);
    }
}
