package com.venkatsai.testpilot.authentication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Name cannot be blank")
        @Email(message = "Invalid Email")
        String email,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 16, message = "Password should be minimum 8 characters")
        String password
) { }
