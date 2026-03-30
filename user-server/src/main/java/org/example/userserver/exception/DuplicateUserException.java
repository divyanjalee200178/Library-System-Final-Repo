package org.example.userserver.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String id) {
        super("Reader with ID '" + id + "' already exists");
    }
}