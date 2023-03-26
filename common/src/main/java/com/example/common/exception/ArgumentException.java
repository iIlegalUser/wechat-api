package com.example.common.exception;

import org.springframework.http.HttpStatus;

public class ArgumentException extends ApiException {

    public ArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }
}
