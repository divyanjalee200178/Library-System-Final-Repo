package org.example.bookserver.service;



import org.example.bookserver.dto.BookRequestDTO;
import org.example.bookserver.dto.BookResponseDTO;

import java.util.List;
public interface BookService {

    BookResponseDTO createBook(BookRequestDTO dto);
    BookResponseDTO updateBook(String bookId, BookRequestDTO dto);
    void deleteBook(String bookId);
    BookResponseDTO getBook(String bookId);
    List<BookResponseDTO> getAllBooks();
}
