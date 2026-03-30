package org.example.lendingserver.exception;

public class LendingNotFoundException extends RuntimeException {
    public LendingNotFoundException(String id) {
        super("Lending with ID '" + id + "' not found");
    }
}


