package com.WorkSphere.WorkSphere.Controllers.Feedback;

import com.WorkSphere.WorkSphere.DTOs.Feedback.FeedBackDTO;
import com.WorkSphere.WorkSphere.Services.Feedback.FeedBackService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedBackController {
    private final FeedBackService feedBackService;

    public FeedBackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addFeedback(@RequestBody FeedBackDTO feedBack, @AuthenticationPrincipal UserDetails userDetails){
        return feedBackService.addFeedback(feedBack, userDetails);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFeedBack(@PathVariable long id){
        return feedBackService.deleteFeedBack(id);
    }
    @GetMapping("/getById/{id}")
    public FeedBackDTO getFeedBackById(@PathVariable long id){
        return feedBackService.getFeedBackById(id);
    }
    @GetMapping("/getBySender/{userId}")
    public ResponseEntity<Object> getFeedBacksBySender(@PathVariable UUID userId, @RequestParam final long pageNumber){
        return feedBackService.getFeedBacksBySender(userId, pageNumber);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllFeedBacks(@RequestParam long pageNumber){
        return feedBackService.getAllFeedBacks(pageNumber);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateFeedBack(@PathVariable long id, @RequestBody FeedBackDTO feedBack){
        return feedBackService.updateFeedBack(id, feedBack);
    }

}
