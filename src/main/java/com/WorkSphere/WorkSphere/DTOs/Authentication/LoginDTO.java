package com.WorkSphere.WorkSphere.DTOs.Authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {
    @Pattern(regexp = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Invalid Email address. Please enter a valid Email.")
    private String email;
    @NotBlank
    private String password;


    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
