package com.WorkSphere.WorkSphere.DTOs.Question;

public record QuestionDTO(
        long id,
        String userName,
        String userEmail,
        String question,
        String answer
) {
}
