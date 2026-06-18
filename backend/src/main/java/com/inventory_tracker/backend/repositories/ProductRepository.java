package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
