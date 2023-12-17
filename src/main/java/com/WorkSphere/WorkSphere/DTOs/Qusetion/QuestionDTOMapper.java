package com.WorkSphere.WorkSphere.DTOs.Qusetion;

import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class QuestionDTOMapper implements Function<Question, QuestionDTO> {
    @Override
    public QuestionDTO apply(Question question){
        return new QuestionDTO(
                question.getId(),
                question.getUserEntity().getFirstName() + " " + question.getUserEntity().getLastName(),
                question.getQuestion(),
                question.getAnswer()
        );
    }
}
