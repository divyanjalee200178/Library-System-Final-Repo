package org.example.bookserver.service.impl;


import org.example.bookserver.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Value("${app.storage.path}")
    private String storagePathStr;

    private Path storagePath;

    private Path getStoragePath() {
        if (storagePath == null) {
            storagePath = Paths.get(storagePathStr);
        }
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
        return storagePath;
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = getStoragePath().resolve(fileName);
        Files.write(filePath, file.getBytes());
        return fileName;
    }

    @Override
    public void deleteImage(String fileName) {
        if (fileName == null) return;
        try {
            Path filePath = getStoragePath().resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {}
    }

    @Override
    public byte[] getImage(String fileName) {
        try {
            Path filePath = getStoragePath().resolve(fileName);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image", e);
        }
    }
}