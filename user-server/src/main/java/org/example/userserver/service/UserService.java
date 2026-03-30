package org.example.userserver.service;



import org.example.userserver.dto.UserRequestDTO;
import org.example.userserver.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO updateUser(String readerId, UserRequestDTO dto);

    void deleteUser(String readerId);

    UserResponseDTO getUser(String readerId);

    List<UserResponseDTO> getAllUsers();
}