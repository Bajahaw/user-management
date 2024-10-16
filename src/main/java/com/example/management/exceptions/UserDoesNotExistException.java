package com.example.management.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserDoesNotExistException extends AuthenticationException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
