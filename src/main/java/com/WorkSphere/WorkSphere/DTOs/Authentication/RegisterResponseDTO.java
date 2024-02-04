package com.WorkSphere.WorkSphere.DTOs.Authentication;

import com.WorkSphere.WorkSphere.DTOs.UserEntity.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {
    private UserDTO userDTO;
    private String confirmationToken;
    private String refreshToken;

    public RegisterResponseDTO(UserDTO userDTO, String confirmationToken, String refreshToken) {
        this.userDTO = userDTO;
        this.confirmationToken = confirmationToken;
        this.refreshToken = refreshToken;
    }
}
