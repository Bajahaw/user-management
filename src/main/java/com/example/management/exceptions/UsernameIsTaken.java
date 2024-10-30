package com.example.management.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class UsernameIsTaken extends ValidationException {
    public UsernameIsTaken(String message) {
        super(message);
    }
}
