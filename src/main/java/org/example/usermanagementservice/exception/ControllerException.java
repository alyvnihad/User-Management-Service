package org.example.usermanagementservice.exception;

import org.springframework.http.HttpStatus;


public abstract class ControllerException extends RuntimeException {
    private final HttpStatus httpStatus;

    protected ControllerException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
