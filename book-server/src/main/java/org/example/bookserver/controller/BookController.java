package org.example.bookserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.bookserver.dto.BookRequestDTO;
import org.example.bookserver.dto.BookResponseDTO;
import org.example.bookserver.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(
            @Validated(BookRequestDTO.OnCreate.class)
            @ModelAttribute BookRequestDTO dto) {

        System.out.println("bookId = " + dto.getBookId());
        System.out.println("picture = " + (dto.getPicture() != null ? dto.getPicture().getOriginalFilename() : "null"));

        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable String bookId,
                                                      @ModelAttribute BookRequestDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(bookId, dto));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}