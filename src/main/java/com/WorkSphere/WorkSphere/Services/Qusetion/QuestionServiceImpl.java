package com.WorkSphere.WorkSphere.Services.Qusetion;

import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import com.WorkSphere.WorkSphere.DTOs.Qusetion.QuestionDTO;
import com.WorkSphere.WorkSphere.DTOs.Qusetion.QuestionDTOMapper;
import com.WorkSphere.WorkSphere.Repositories.QuestionRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionDTOMapper questionDTOMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionDTOMapper questionDTOMapper) {
        this.questionRepository = questionRepository;
        this.questionDTOMapper = questionDTOMapper;
    }

    @Override
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public ResponseEntity<Object> getAllUserQuestions(UUID userId, final long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber - 1, 10);
        final List<QuestionDTO> userQuestions = questionRepository.fetchAllUserQuesions(userId,pageable).stream().map(questionDTOMapper).toList();
        if(userQuestions.isEmpty() && pageNumber > 1)
        {
            return getAllUserQuestions(userId,1);
        }
        return ResponseHandler.generateResponse(userQuestions, HttpStatus.OK,userQuestions.size(),questionRepository.getUserQuestionsNumber(userId));
    }
}
