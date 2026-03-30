package org.example.lendingserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "book-service", url = "http://localhost:8082")
public interface BookServiceClient {
    @GetMapping("/api/v1/books/{id}")
    Map<String, Object> getBookById(@PathVariable String id);

    @GetMapping("/api/v1/books")
    List<Map<String, Object>> getAllBooks();
}
