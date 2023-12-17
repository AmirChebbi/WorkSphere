package com.WorkSphere.WorkSphere.Services.Feedback;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import com.WorkSphere.WorkSphere.DTOs.Feedback.FeedBackDTO;
import com.WorkSphere.WorkSphere.DTOs.Feedback.FeedBackDTOMapper;
import com.WorkSphere.WorkSphere.Exceptions.ResourceNotFoundException;
import com.WorkSphere.WorkSphere.Repositories.FeedBackRepository;
import com.WorkSphere.WorkSphere.responses.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedBackServiceImpl implements FeedBackService{

    private final FeedBackRepository feedBackRepository;
    private final FeedBackDTOMapper feedBackDTOMapper;

    public FeedBackServiceImpl(FeedBackRepository feedBackRepository, FeedBackDTOMapper feedBackDTOMapper) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackDTOMapper = feedBackDTOMapper;
    }

    @Override
    public ResponseEntity<Object> addFeedback(FeedBack feedBack) {
        feedBackRepository.save(feedBack);
        final String successResponse = String.format("Your FeedBack was sent successfully ");
        return ResponseHandler.generateResponse(successResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteFeedBack(long id) {
        feedBackRepository.deleteById(id);
        final String successResponse = String.format("Your feedback was deleted successfully ");
        return ResponseHandler.generateResponse(successResponse, HttpStatus.OK);
    }

    @Override
    public FeedBackDTO getFeedBackById(long id) {
        FeedBack feedBack = feedBackRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("This feedback doesn't exist in our System"));
        return new FeedBackDTOMapper().apply(feedBack);
    }

    @Override
    public ResponseEntity<Object> getFeedBacksBySender(UUID userId, final long pageNumber) {
        final Pageable pageable = PageRequest.of((int)pageNumber - 1, 10 );
        final List<FeedBackDTO> userFeedBacks = feedBackRepository.findFeedBackBySender(userId).stream().map(feedBackDTOMapper).toList();

        if(userFeedBacks.isEmpty() && pageNumber > 1)
        {
            return getFeedBacksBySender(userId,1);
        }
        final long total = feedBackRepository.getUserFeedBackCount(userId);
        return ResponseHandler.generateResponse(userFeedBacks, HttpStatus.OK, userFeedBacks.size(),total);
    }

    @Override
    public ResponseEntity<Object> getAllFeedBacks(long pageNumber) {
        final Pageable pageable  = PageRequest.of((int)pageNumber - 1, 10 );
        final List<FeedBackDTO> feedBacks =feedBackRepository.fetchAll((java.awt.print.Pageable) pageable).stream().map(feedBackDTOMapper).toList();
        if(feedBacks.isEmpty() && pageNumber > 1)
        {
            return getAllFeedBacks(1);
        }
        final long total = feedBackRepository.getTotalFeedBackCount();
        return ResponseHandler.generateResponse(feedBacks, HttpStatus.OK,feedBacks.size(),total);

    }

    @Override
    public ResponseEntity<Object> updateFeedBack(long id, FeedBack feedBack) {
        FeedBack feedBackChecking = feedBackRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This feedback doesn't exist in our system") );
        feedBack.setId(id);
        feedBackRepository.save(feedBack);
        final String successResponse = String.format("Your FeedBack was updated successfully !");
        return ResponseHandler.generateResponse(successResponse, HttpStatus.OK);
    }
}
