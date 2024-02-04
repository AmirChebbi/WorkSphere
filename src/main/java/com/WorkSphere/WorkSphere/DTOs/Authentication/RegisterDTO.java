package com.WorkSphere.WorkSphere.DTOs.Authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @Size(max = 20, message ="Invalid first name length. Please enter a name with a minimum of 5 characters and a maximum of 20 characters.")
    private String firstName;
    @Size(max = 20, message ="Invalid last name length. Please enter a name with a minimum of 5 characters and a maximum of 20 characters.")
    private String lastName;
    @Pattern(regexp = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "Invalid Email address. Please enter a valid Email.")
    private String email;
    @NotBlank
    private String phoneNumber;
    /*/@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password. Passwords must be at least 8 characters long and include at least one uppercase letter, " +
                    "one lowercase letter, one digit, and one special character.")*/
    private String password;

    public RegisterDTO(String fistName, String lastName, String email , String phoneNumber  , String password) {
        this.firstName = fistName;
        this.lastName  = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
