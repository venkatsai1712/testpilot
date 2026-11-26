package com.venkatsai.testpilot.authentication.dto.response;

public record ErrorResponse(
        String message,
        String details,
        String code
) {
}
