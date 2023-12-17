package com.WorkSphere.WorkSphere.DTOs.Qusetion;

public record QuestionDTO(
        long id,
        String userEntity,
        String question,
        String answer
) {
}
