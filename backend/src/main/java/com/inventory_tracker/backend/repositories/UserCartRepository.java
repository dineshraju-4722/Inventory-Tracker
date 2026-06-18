package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCartRepository extends JpaRepository<UserCart,Long> {
}
