package org.example.lendingserver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "lendings")
public class Lending {

    @Id
    private String lendingId;
    private String readerId;
    private String bookId;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public Lending() {}

    public Lending(String lendingId, String readerId, String bookId,
                   LocalDate borrowedDate, LocalDate dueDate, LocalDate returnDate, String status) {
        this.lendingId = lendingId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getLendingId() { return lendingId; }
    public void setLendingId(String lendingId) { this.lendingId = lendingId; }

    public String getReaderId() { return readerId; }
    public void setReaderId(String readerId) { this.readerId = readerId; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public LocalDate getBorrowedDate() { return borrowedDate; }
    public void setBorrowedDate(LocalDate borrowedDate) { this.borrowedDate = borrowedDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}