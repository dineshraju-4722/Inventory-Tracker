package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.model.ImportedHistory;
import com.inventory_tracker.backend.payload.CategoryDTO;
import com.inventory_tracker.backend.payload.ImportedHistoryRequestDTO;
import com.inventory_tracker.backend.payload.ImportedHistoryResponseDTO;
import com.inventory_tracker.backend.service.CategoryService;
import com.inventory_tracker.backend.service.ImportedHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImportedHistoryController {

    @Autowired
    private ImportedHistoryService importedHistoryService;

    @PostMapping("/importedhistory")
    public ResponseEntity<ImportedHistoryResponseDTO> addCategory(@RequestBody ImportedHistoryRequestDTO  importedHistoryRequestDTO){
        ImportedHistoryResponseDTO importedHistoryResponseDTO = importedHistoryService.addImportedProduct(importedHistoryRequestDTO);
        return new ResponseEntity<>(importedHistoryResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/importedhistories")
    public ResponseEntity<List<ImportedHistoryResponseDTO>> getAllImportedHistories(){
         List<ImportedHistoryResponseDTO> importedHistoryResponseDTOs = importedHistoryService.getAllImportedHistories();
         return new ResponseEntity<>(importedHistoryResponseDTOs,HttpStatus.OK);
    }

    @PutMapping("/importedhistory/{id}")
    public ResponseEntity<ImportedHistoryResponseDTO> updateImportedHistory(@PathVariable Long id,
                                                                             @RequestBody ImportedHistoryRequestDTO importedHistoryRequestDTO){
        ImportedHistoryResponseDTO importedHistoryResponseDTO = importedHistoryService.updateImportedHistory(id,importedHistoryRequestDTO);
        return new ResponseEntity<>(importedHistoryResponseDTO,HttpStatus.OK);
    }

    
}
