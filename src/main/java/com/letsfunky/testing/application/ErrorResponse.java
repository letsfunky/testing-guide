package com.letsfunky.testing.application;

import lombok.Value;

@Value
public class ErrorResponse {
    String message;

    public static ErrorResponse of(Exception e) {
        return new ErrorResponse(e.getMessage());
    }
}
