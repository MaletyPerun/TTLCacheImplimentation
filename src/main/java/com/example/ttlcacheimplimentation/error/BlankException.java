package com.example.ttlcacheimplimentation.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class BlankException extends AppException {
    public BlankException(String message) {
        super(HttpStatus.BAD_REQUEST, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
