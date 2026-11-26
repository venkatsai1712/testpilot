package com.venkatsai.testpilot.authentication.controller;

import com.venkatsai.testpilot.authentication.dto.request.LoginRequest;
import com.venkatsai.testpilot.authentication.dto.request.RegisterRequest;
import com.venkatsai.testpilot.authentication.dto.response.UserResponse;
import com.venkatsai.testpilot.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserResponse userResponse = authenticationService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = authenticationService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
