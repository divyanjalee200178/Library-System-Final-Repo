package org.example.lendingserver.service;

import org.example.lendingserver.dto.LendingRequestDTO;
import org.example.lendingserver.dto.LendingResponseDTO;

import java.util.List;

public interface LendingService {
    LendingResponseDTO createLending(LendingRequestDTO dto);
    LendingResponseDTO updateLending(String lendingId, LendingRequestDTO dto);
    void deleteLending(String lendingId);
    LendingResponseDTO getLending(String lendingId);
    List<LendingResponseDTO> getAllLendings();
}