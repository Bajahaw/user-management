package com.example.management.exceptions;

public class UserDeosNotExistException extends RuntimeException {
    public UserDeosNotExistException(String message) {
        super(message);
    }
}
