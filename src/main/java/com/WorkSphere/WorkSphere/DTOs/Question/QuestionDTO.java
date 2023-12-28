package com.WorkSphere.WorkSphere.DTOs.Question;

public record QuestionDTO(
        long id,
        String userEntity,
        String question,
        String answer
) {
}
