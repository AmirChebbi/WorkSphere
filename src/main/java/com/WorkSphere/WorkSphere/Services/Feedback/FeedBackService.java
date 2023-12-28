package com.WorkSphere.WorkSphere.Services.Feedback;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import com.WorkSphere.WorkSphere.DTOs.Feedback.FeedBackDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface FeedBackService {
    public ResponseEntity<Object> addFeedback(FeedBackDTO feedBack);
    public ResponseEntity<Object> deleteFeedBack(long id);
    public FeedBackDTO getFeedBackById(long id);
    public ResponseEntity<Object> getFeedBacksBySender(UUID userId,final long pageNumber);
    public ResponseEntity<Object> getAllFeedBacks(long pageNumber);
    public ResponseEntity<Object> updateFeedBack(long id, FeedBackDTO feedBackDTO);
}
