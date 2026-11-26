package com.venkatsai.testpilot.authentication.service;

import com.venkatsai.testpilot.authentication.dto.request.LoginRequest;
import com.venkatsai.testpilot.authentication.dto.request.RegisterRequest;
import com.venkatsai.testpilot.authentication.dto.response.UserResponse;
import com.venkatsai.testpilot.authentication.entity.User;
import com.venkatsai.testpilot.authentication.exception.InvalidCredentialsException;
import com.venkatsai.testpilot.authentication.exception.InvalidEmailException;
import com.venkatsai.testpilot.authentication.exception.InvalidPasswordException;
import com.venkatsai.testpilot.authentication.exception.InvalidUserNameException;
import com.venkatsai.testpilot.authentication.repository.AuthenticationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    @Transactional
    public UserResponse register(RegisterRequest registerRequest) {
        if(registerRequest.name().isEmpty()) {
            throw new InvalidUserNameException("Name is invalid");
        }
        if(!registerRequest.email().contains("@gmail.com")) {
            throw new InvalidEmailException("Email is invalid");
        }
        if(registerRequest.password().length() < 8) {
            throw new InvalidPasswordException("Password length is less than 8 characters");
        }
        User user = new User(registerRequest.name(), registerRequest.email(), registerRequest.password());
        User savedUser = authenticationRepository.save(user);
        return mapToUserResponse(savedUser);
    }

    @Transactional
    public UserResponse login(LoginRequest loginRequest) {
        if(!loginRequest.email().contains("@gmail.com")) {
            throw new InvalidEmailException("Email is invalid");
        }
        if(loginRequest.password().length() < 8) {
            throw new InvalidPasswordException("Password length is less than 8 characters");
        }
        User user = authenticationRepository.findByEmail(loginRequest.email());
        if(user == null || !user.getPasswordHash().equals(loginRequest.password())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return mapToUserResponse(user);
    }

    public UserResponse mapToUserResponse(User savedUser) {
        return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());
    }
}
