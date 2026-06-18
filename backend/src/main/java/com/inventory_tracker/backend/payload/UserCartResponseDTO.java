package com.inventory_tracker.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCartResponseDTO {
    private Long userCartId;

    private Long quantity;

    private  ProductResponseDTO product;

}
