package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Category;
import com.inventory_tracker.backend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
