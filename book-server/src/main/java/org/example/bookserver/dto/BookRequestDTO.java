package org.example.bookserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.example.bookserver.validation.ValidImage;
import org.springframework.web.multipart.MultipartFile;


public class BookRequestDTO {
    public interface OnCreate {}

    @NotBlank(groups = OnCreate.class, message = "Book ID is required")
    @Pattern(groups = OnCreate.class, regexp = "^B\\d{3}$", message = "Book ID must be like B001")
    private String bookId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Publisher is required")
    private String publisher;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(groups = OnCreate.class, message = "Picture is required")
    @ValidImage
    private MultipartFile picture;

    // Getters and setters
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public MultipartFile getPicture() { return picture; }
    public void setPicture(MultipartFile picture) { this.picture = picture; }
}