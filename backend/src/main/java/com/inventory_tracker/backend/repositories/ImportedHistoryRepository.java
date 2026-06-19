package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Category;
import com.inventory_tracker.backend.model.ImportedHistory;
import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportedHistoryRepository extends JpaRepository<ImportedHistory
        ,Long> {
    ImportedHistory findByUserAndProduct(User user, Product product);

    List<ImportedHistory> findAllByUser(User loggedInUser);
}
