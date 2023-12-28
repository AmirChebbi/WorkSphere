package com.WorkSphere.WorkSphere.DTOs.Question;

import com.WorkSphere.WorkSphere.DAOs.Qusetion.Question;
import com.WorkSphere.WorkSphere.Services.UserEntity.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class QuestionDTOMapper implements Function<Question, QuestionDTO> {
    private final UserService userService;

    public QuestionDTOMapper(UserService userService) {
        this.userService = userService;
    }

    @Override
    public QuestionDTO apply(Question question){
        return new QuestionDTO(
                question.getId(),
                question.getUserEntity().getFirstName() + " " + question.getUserEntity().getLastName(),
                question.getUserEntity().getEmail(),
                question.getQuestion(),
                question.getAnswer()
        );
    }

    public Question reverse(QuestionDTO questionDTO, UserDetails userDetails){
        return new Question(
                questionDTO.id(),
                userService.getUserEntityByEmail(userDetails.getUsername()),
                questionDTO.question(),
                questionDTO.answer()
        );
    }
}
