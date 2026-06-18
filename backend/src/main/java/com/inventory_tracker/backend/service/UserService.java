package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.payload.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();
}
