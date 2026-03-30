package org.example.userserver.service.serviceImpl;

import org.example.userserver.dto.UserRequestDTO;
import org.example.userserver.dto.UserResponseDTO;
import org.example.userserver.entity.User;
import org.example.userserver.exception.DuplicateUserException;
import org.example.userserver.exception.UserNotFoundException;
import org.example.userserver.repository.UserRepository;
import org.example.userserver.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setMobile(dto.getMobile());
        user.setEmail(dto.getEmail());
        user.setMembershipType(dto.getMembershipType());
        user.setRole(User.Role.valueOf(dto.getRole().toUpperCase()));

        User saved = userRepository.save(user);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(String userId, UserRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setMobile(dto.getMobile());
        user.setEmail(dto.getEmail());
        user.setMembershipType(dto.getMembershipType());
        user.setRole(User.Role.valueOf(dto.getRole().toUpperCase()));

        User updated = userRepository.save(user);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return mapToDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserResponseDTO mapToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId().toString());
        dto.setName(user.getName());
        dto.setAddress(user.getAddress());
        dto.setMobile(user.getMobile());
        dto.setEmail(user.getEmail());
        dto.setMembershipType(user.getMembershipType());
        dto.setRole(user.getRole().name());
        return dto;
    }
}