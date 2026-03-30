package org.example.bookserver.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String bookId) {
        super("Book with ID '" + bookId + "' already exists");
    }
}
