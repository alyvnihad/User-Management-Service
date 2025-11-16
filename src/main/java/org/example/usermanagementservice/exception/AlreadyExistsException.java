package org.example.usermanagementservice.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ControllerException {
    public AlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
