package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.payload.UserCartRequestDTO;
import com.inventory_tracker.backend.payload.UserCartResponseDTO;

import java.util.List;

public interface UserCartService {
    UserCartResponseDTO addUserCart(UserCartRequestDTO userCartRequestDTO);

    List<UserCartResponseDTO> getUsersCart();

    List<UserCartResponseDTO> getUserCartById(Long userId);
}
