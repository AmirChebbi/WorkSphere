package com.WorkSphere.WorkSphere.DTOs.Feedback;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import com.WorkSphere.WorkSphere.Services.UserEntity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class FeedBackDTOMapper implements Function<FeedBack, FeedBackDTO> {
    @Autowired
    private UserService userService;
    @Override
    public FeedBackDTO apply(FeedBack feedBack){
        return new FeedBackDTO(
                feedBack.getId(),
                feedBack.getSender().getFirstName() + " " + feedBack.getSender().getLastName(),
                feedBack.getSender().getEmail(),
                feedBack.getMessage()
        );
    }

    public FeedBack reverse(FeedBackDTO feedBackDTO){
        return new FeedBack(
                feedBackDTO.id(),
                userService.getUserEntityByEmail(feedBackDTO.senderEmail()),
                feedBackDTO.message()
        );
    }
}
