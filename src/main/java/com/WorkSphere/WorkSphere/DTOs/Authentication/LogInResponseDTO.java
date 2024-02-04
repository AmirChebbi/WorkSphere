package com.WorkSphere.WorkSphere.DTOs.Authentication;

import com.WorkSphere.WorkSphere.DTOs.UserEntity.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInResponseDTO {
    private UserDTO userDTO;
    private String accessToken;
    private String refreshToken;
}
