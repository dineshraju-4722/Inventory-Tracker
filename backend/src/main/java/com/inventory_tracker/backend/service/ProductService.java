package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.payload.ProductRequestDTO;
import com.inventory_tracker.backend.payload.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();
}
