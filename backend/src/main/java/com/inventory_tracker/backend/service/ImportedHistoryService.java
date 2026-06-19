package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.payload.ImportedHistoryRequestDTO;
import com.inventory_tracker.backend.payload.ImportedHistoryResponseDTO;

import java.util.List;

public interface ImportedHistoryService {
    ImportedHistoryResponseDTO addImportedProduct(ImportedHistoryRequestDTO importedHistoryRequestDTO);

    List<ImportedHistoryResponseDTO> getAllImportedHistories();

    ImportedHistoryResponseDTO updateImportedHistory(Long id, ImportedHistoryRequestDTO importedHistoryRequestDTO);
}
