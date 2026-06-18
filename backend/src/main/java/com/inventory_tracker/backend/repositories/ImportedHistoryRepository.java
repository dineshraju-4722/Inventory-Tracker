package com.inventory_tracker.backend.repositories;

import com.inventory_tracker.backend.model.Category;
import com.inventory_tracker.backend.model.ImportedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportedHistoryRepository extends JpaRepository<ImportedHistory
        ,Long> {
}
