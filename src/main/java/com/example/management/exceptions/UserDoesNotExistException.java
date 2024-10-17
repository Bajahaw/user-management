package com.example.management.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDoesNotExistException extends UsernameNotFoundException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
