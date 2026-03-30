package org.example.lendingserver.controller;

import org.example.lendingserver.client.BookServiceClient;
import org.example.lendingserver.client.UserServiceClient;
import org.example.lendingserver.dto.LendingRequestDTO;
import org.example.lendingserver.dto.LendingResponseDTO;
import org.example.lendingserver.service.LendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/lendings")
public class LendingController {

    private final LendingService lendingService;
    private final UserServiceClient userServiceClient;
    private final BookServiceClient bookServiceClient;

    public LendingController(LendingService lendingService, UserServiceClient userServiceClient, BookServiceClient bookServiceClient) {
        this.lendingService = lendingService;
        this.userServiceClient = userServiceClient;
        this.bookServiceClient = bookServiceClient;
    }

    @PostMapping
    public ResponseEntity<LendingResponseDTO> createLending(@RequestBody LendingRequestDTO dto) {
        return ResponseEntity.status(201).body(lendingService.createLending(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LendingResponseDTO> updateLending(@PathVariable String id,
                                                            @RequestBody LendingRequestDTO dto) {
        return ResponseEntity.ok(lendingService.updateLending(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLending(@PathVariable String id) {
        lendingService.deleteLending(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LendingResponseDTO> getLending(@PathVariable String id) {
        return ResponseEntity.ok(lendingService.getLending(id));
    }

    // ✅ Unique path to avoid conflict
    @GetMapping("/with-user/{id}")
    public ResponseEntity<Map<String, Object>> getLendingWithUser(@PathVariable String id) {
        LendingResponseDTO lending = lendingService.getLending(id);
        Map<String, Object> user = userServiceClient.getUserById(lending.getReaderId());

        Map<String, Object> response = new HashMap<>();
        response.put("lending", lending);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LendingResponseDTO>> getAllLendings() {
        return ResponseEntity.ok(lendingService.getAllLendings());
    }

    @GetMapping("/readers")
    public ResponseEntity<List<Map<String, String>>> getAllReaders() {
        try {
            List<Map<String, Object>> users = userServiceClient.getAllUsers();

            // Debug: print all users to console
            users.forEach(u -> System.out.println(u));

            // Map with correct keys
            List<Map<String, String>> result = users.stream()
                    .map(u -> Map.of(
                            "readerId", u.get("id") != null ? u.get("id").toString()
                                    : u.get("userId") != null ? u.get("userId").toString() : "unknown",
                            "name", u.get("name") != null ? u.get("name").toString()
                                    : u.get("fullName") != null ? u.get("fullName").toString() : "Unnamed"
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Map<String, String>>> getAllBooks() {

        List<Map<String, Object>> books = bookServiceClient.getAllBooks();

        List<Map<String, String>> result = books.stream()
                .map(b -> {
                    String id = b.get("id") != null
                            ? b.get("id").toString()
                            : UUID.randomUUID().toString();

                    String title = b.get("title") != null
                            ? b.get("title").toString()
                            : "Untitled";

                    return Map.of(
                            "bookId", id,
                            "title", title
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}