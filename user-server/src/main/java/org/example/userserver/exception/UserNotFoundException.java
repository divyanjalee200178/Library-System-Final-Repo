package org.example.userserver.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("Reader with ID '" + id + "' not found");
    }
}