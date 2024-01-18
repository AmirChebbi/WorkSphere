package com.WorkSphere.WorkSphere.Controllers.Feedback;

import com.WorkSphere.WorkSphere.DAOs.Feedback.FeedBack;
import com.WorkSphere.WorkSphere.DTOs.Feedback.FeedBackDTO;
import com.WorkSphere.WorkSphere.Services.Feedback.FeedBackService;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<Object> addFeedback(@RequestBody FeedBackDTO feedBack){
        return feedBackService.addFeedback(feedBack);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFeedBack(@PathVariable long id){
        return feedBackService.deleteFeedBack(id);
    }
    @GetMapping("/getById/{id}")
    public FeedBackDTO getFeedBackById(@PathVariable long id){
        return feedBackService.getFeedBackById(id);
    }
    @GetMapping("/getBySender")
    public ResponseEntity<Object> getFeedBacksBySender(@RequestParam String email, @RequestParam final long pageNumber){
        return feedBackService.getFeedBacksBySender(email, pageNumber);
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
