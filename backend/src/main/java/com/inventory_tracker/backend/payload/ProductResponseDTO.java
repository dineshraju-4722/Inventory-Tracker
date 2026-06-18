package com.inventory_tracker.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long productId;

    private String productName;

    private CategoryDTO category;


    private SupplierDTO supplier;
}
