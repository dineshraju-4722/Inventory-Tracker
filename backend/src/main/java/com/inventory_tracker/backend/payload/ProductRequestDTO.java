package com.inventory_tracker.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private Long productId;

    private String productName;

    private Long categoryId;


    private Long supplierId;
}
