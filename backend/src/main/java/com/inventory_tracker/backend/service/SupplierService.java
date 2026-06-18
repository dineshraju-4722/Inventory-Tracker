package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.payload.SupplierDTO;

import java.util.List;

public interface SupplierService {
    SupplierDTO addSupplier(SupplierDTO supplierDTO);

    List<SupplierDTO> getAllSuppliers();
}
