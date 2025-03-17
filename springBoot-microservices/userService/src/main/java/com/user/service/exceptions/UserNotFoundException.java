package com.user.service.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Resource not found!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
