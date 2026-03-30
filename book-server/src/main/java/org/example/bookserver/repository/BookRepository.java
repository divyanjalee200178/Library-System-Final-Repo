package org.example.bookserver.repository;

import org.example.bookserver.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    // String is the type of bookId (assumes your Book entity has `bookId` as @Id)

    // Optional: add custom queries if needed
    boolean existsByTitle(String title); // for example, to check duplicate titles
}