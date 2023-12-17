package com.WorkSphere.WorkSphere.DTOs.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponseDTO {
    private String accessToken;
    private String refreshToken;
}