package com.WorkSphere.WorkSphere.DTOs.Feedback;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class FeedBackDTOMapper implements Function<FeedBack, FeedBackDTO> {
    @Override
    public FeedBackDTO apply(FeedBack feedBack){
        return new FeedBackDTO(
                feedBack.getId(),
                feedBack.getMessage(),
                feedBack.getSender().getFirstName() + " " + feedBack.getSender().getLastName()
        );
    }
}
