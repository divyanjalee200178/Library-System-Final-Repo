package org.example.bookserver.exception;

public class BookNotFoundException  extends RuntimeException {
    public BookNotFoundException(String bookId) {
        super("Book with ID '" + bookId + "' not found");
    }
}