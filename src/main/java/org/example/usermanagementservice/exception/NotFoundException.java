package org.example.usermanagementservice.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ControllerException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
