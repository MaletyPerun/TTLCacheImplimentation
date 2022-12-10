package com.example.ttlcacheimplimentation.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class UnprocessableEntityException extends AppException {
    public UnprocessableEntityException(String message) {
        super(HttpStatus.NO_CONTENT, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
