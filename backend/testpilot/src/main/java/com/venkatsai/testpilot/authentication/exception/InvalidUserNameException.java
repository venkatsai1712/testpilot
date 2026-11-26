package com.venkatsai.testpilot.authentication.exception;

public class InvalidUserNameException extends RuntimeException{
    public InvalidUserNameException(String message) {
        super(message);
    }
}
