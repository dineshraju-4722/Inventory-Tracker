package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
