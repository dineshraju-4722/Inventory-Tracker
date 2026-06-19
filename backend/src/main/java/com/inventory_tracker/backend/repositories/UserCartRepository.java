package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCartRepository extends JpaRepository<UserCart,Long> {
    List<UserCart> findByUser_UserId(Long userUserId);

    UserCart findByUserAndProduct(User user, Product product);
}
