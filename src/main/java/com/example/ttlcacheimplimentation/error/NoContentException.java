package com.example.ttlcacheimplimentation.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class NoContentException extends AppException {
    public NoContentException(String message) {
        super(HttpStatus.NO_CONTENT, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
