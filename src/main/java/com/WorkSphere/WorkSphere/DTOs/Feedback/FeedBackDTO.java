package com.WorkSphere.WorkSphere.DTOs.Feedback;

public record FeedBackDTO(
        long id,
        String senderName,
        String senderEmail,
        String message

) {
}
