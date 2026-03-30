package org.example.lendingserver.exception;

public class DuplicateLendingException extends RuntimeException {
    public DuplicateLendingException(String id) {
        super("Lending with ID '" + id + "' already exists");
    }
}