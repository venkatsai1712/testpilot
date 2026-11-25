package com.venkatsai.testpilot.authentication.service;

import com.venkatsai.testpilot.authentication.dto.request.LoginRequest;
import com.venkatsai.testpilot.authentication.dto.request.RegisterRequest;
import com.venkatsai.testpilot.authentication.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public UserResponse register(RegisterRequest registerRequest) {
        return null;
    }

    public UserResponse login(LoginRequest loginRequest) {
        return null;
    }
}
