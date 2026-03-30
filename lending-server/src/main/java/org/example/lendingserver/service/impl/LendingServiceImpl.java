package org.example.lendingserver.service.impl;

import org.example.lendingserver.client.UserServiceClient;
import org.example.lendingserver.dto.LendingRequestDTO;
import org.example.lendingserver.dto.LendingResponseDTO;
import org.example.lendingserver.entity.Lending;
import org.example.lendingserver.exception.LendingNotFoundException;
import org.example.lendingserver.repository.LendingRepository;
import org.example.lendingserver.service.LendingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LendingServiceImpl implements LendingService {

    private final LendingRepository lendingRepository;
    private final UserServiceClient userServiceClient;

    public LendingServiceImpl(LendingRepository lendingRepository, UserServiceClient userServiceClient) {
        this.lendingRepository = lendingRepository;
        this.userServiceClient = userServiceClient;
    }

    @Override
    public LendingResponseDTO createLending(LendingRequestDTO dto) {
        String id = dto.getLendingId() != null ? dto.getLendingId() : UUID.randomUUID().toString();

        Lending lending = new Lending(id, dto.getReaderId(), dto.getBookId(),
                dto.getBorrowedDate(), dto.getDueDate(), dto.getReturnDate(), dto.getStatus());

        Lending saved = lendingRepository.save(lending);
        return mapToDTO(saved);
    }

    @Override
    public LendingResponseDTO updateLending(String lendingId, LendingRequestDTO dto) {
        Lending lending = lendingRepository.findById(lendingId)
                .orElseThrow(() -> new LendingNotFoundException(lendingId));

        lending.setReaderId(dto.getReaderId());
        lending.setBookId(dto.getBookId());
        lending.setBorrowedDate(dto.getBorrowedDate());
        lending.setDueDate(dto.getDueDate());
        lending.setReturnDate(dto.getReturnDate());
        lending.setStatus(dto.getStatus());

        Lending updated = lendingRepository.save(lending);
        return mapToDTO(updated);
    }

    @Override
    public void deleteLending(String lendingId) {
        Lending lending = lendingRepository.findById(lendingId)
                .orElseThrow(() -> new LendingNotFoundException(lendingId));
        lendingRepository.delete(lending);
    }

    @Override
    public LendingResponseDTO getLending(String lendingId) {
        Lending lending = lendingRepository.findById(lendingId)
                .orElseThrow(() -> new LendingNotFoundException(lendingId));
        return mapToDTO(lending);
    }

    @Override
    public List<LendingResponseDTO> getAllLendings() {
        return lendingRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LendingResponseDTO mapToDTO(Lending lending) {
        LendingResponseDTO dto = new LendingResponseDTO();
        dto.setLendingId(lending.getLendingId());
        dto.setReaderId(lending.getReaderId());
        dto.setBookId(lending.getBookId());
        dto.setBorrowedDate(lending.getBorrowedDate());
        dto.setDueDate(lending.getDueDate());
        dto.setReturnDate(lending.getReturnDate());
        dto.setStatus(lending.getStatus());
        return dto;
    }
}