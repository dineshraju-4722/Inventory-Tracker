package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Category;
import com.inventory_tracker.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
