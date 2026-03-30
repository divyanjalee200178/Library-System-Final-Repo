package org.example.bookserver.service.impl;

import org.example.bookserver.dto.BookRequestDTO;
import org.example.bookserver.dto.BookResponseDTO;
import org.example.bookserver.entity.Book;
import org.example.bookserver.repository.BookRepository;
import org.example.bookserver.service.BookService;
import org.example.bookserver.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final FileService fileService;

    public BookServiceImpl(BookRepository bookRepository, FileService fileService) {
        this.bookRepository = bookRepository;
        this.fileService = fileService;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {
        if (bookRepository.existsById(dto.getBookId()))
            throw new RuntimeException("Book already exists: " + dto.getBookId());

        String pictureUrl = null;
        try {
            if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                pictureUrl = fileService.uploadImage(dto.getPicture());
            }
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }

        Book book = new Book();
        book.setBookId(dto.getBookId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setIsbn(dto.getIsbn());
        book.setPicture(pictureUrl);

        return toResponse(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO updateBook(String bookId, BookRequestDTO dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        try {
            if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                fileService.deleteImage(book.getPicture());
                book.setPicture(fileService.uploadImage(dto.getPicture()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Image update failed", e);
        }

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setIsbn(dto.getIsbn());

        return toResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        fileService.deleteImage(book.getPicture());
        bookRepository.delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDTO getBook(String bookId) {
        return bookRepository.findById(bookId)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private BookResponseDTO toResponse(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setYear(book.getYear());
        dto.setIsbn(book.getIsbn());
        dto.setPictureUrl(book.getPicture());
        return dto;
    }
}