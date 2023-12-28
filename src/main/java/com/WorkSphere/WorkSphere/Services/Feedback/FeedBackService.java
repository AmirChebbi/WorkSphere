package com.WorkSphere.WorkSphere.Services.Feedback;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import com.WorkSphere.WorkSphere.DTOs.Feedback.FeedBackDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface FeedBackService {
    public ResponseEntity<Object> addFeedback(FeedBackDTO feedBack, UserDetails userDetails);
    public ResponseEntity<Object> deleteFeedBack(long id);
    public FeedBackDTO getFeedBackById(long id);
    public ResponseEntity<Object> getFeedBacksBySender(UUID userId,final long pageNumber);
    public ResponseEntity<Object> getAllFeedBacks(long pageNumber);
    public ResponseEntity<Object> updateFeedBack(long id, FeedBackDTO feedBackDTO);
}
