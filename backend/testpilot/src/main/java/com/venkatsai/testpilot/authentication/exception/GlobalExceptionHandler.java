package com.venkatsai.testpilot.authentication.exception;

import com.venkatsai.testpilot.authentication.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserNameException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserName(InvalidUserNameException exception) {
        ErrorResponse res = new ErrorResponse(exception.getMessage(), "Please provide valid name, it should not be empty", "INVALID_NAME");
        return ResponseEntity.status(422).body(res);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmail(InvalidEmailException exception) {
        ErrorResponse res = new ErrorResponse(exception.getMessage(), "Please provide valid email, it should ends with @gmail.com", "INVALID_EMAIL");
        return ResponseEntity.status(422).body(res);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword(InvalidPasswordException exception) {
        ErrorResponse res = new ErrorResponse(exception.getMessage(), "Please provide valid password, it should contain more than 8 characters", "INVALID_PASSWORD");
        return ResponseEntity.status(422).body(res);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException exception) {
        ErrorResponse res = new ErrorResponse(exception.getMessage(), "Please provide valid email and password", "INVALID_CREDENTIALS");
        return ResponseEntity.status(401).body(res);
    }
}
