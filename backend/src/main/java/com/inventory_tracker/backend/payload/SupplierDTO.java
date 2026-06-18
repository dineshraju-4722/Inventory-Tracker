package com.inventory_tracker.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private Long supplierId;

    private String companyName;

    private String email;

    private Integer pinCode;
}
