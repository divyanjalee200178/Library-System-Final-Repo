package org.example.bookserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadImage(MultipartFile file) throws IOException;
    void deleteImage(String fileName);
    byte[] getImage(String fileName);
}
