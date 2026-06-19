package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.model.ImportedHistory;
import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.Supplier;
import com.inventory_tracker.backend.model.User;
import com.inventory_tracker.backend.payload.*;
import com.inventory_tracker.backend.repositories.ImportedHistoryRepository;
import com.inventory_tracker.backend.repositories.ProductRepository;
import com.inventory_tracker.backend.repositories.SupplierRepository;
import com.inventory_tracker.backend.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImportedHistoryServiceImpl implements  ImportedHistoryService {
    @Autowired
    private ImportedHistoryRepository importedHistoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ImportedHistoryResponseDTO addImportedProduct(ImportedHistoryRequestDTO importedHistoryRequestDTO) {
        Product product = productRepository.findById(importedHistoryRequestDTO.getProductId()).orElse(null);
        User user = userRepository.findById(importedHistoryRequestDTO.getUserId()).orElse(null);
        ImportedHistory importedHistory = new ImportedHistory();
        importedHistory.setQuantity(importedHistoryRequestDTO.getQuantity());
        importedHistory.setProduct(product);
        importedHistory.setUser(user);
        ImportedHistory saved = importedHistoryRepository.save(importedHistory);
        return getImportedHistoryResponseDTO(saved);
    }
    @NonNull
    private ImportedHistoryResponseDTO getImportedHistoryResponseDTO(ImportedHistory saved) {
        ImportedHistoryResponseDTO importedHistoryResponseDTO = modelMapper.map(saved, ImportedHistoryResponseDTO.class);
        ProductResponseDTO productResponseDTO = modelMapper.map(saved.getProduct(),ProductResponseDTO.class);
        CategoryDTO categoryDTO = modelMapper.map(saved.getProduct().getCategory(), CategoryDTO.class);
        SupplierDTO supplierDTO = modelMapper.map( saved.getProduct().getSupplier() , SupplierDTO.class );
        productResponseDTO.setCategory(categoryDTO);
        productResponseDTO.setSupplier(supplierDTO);
        importedHistoryResponseDTO.setProduct(productResponseDTO);
        return importedHistoryResponseDTO;
    }

    @Override
    public List<ImportedHistoryResponseDTO> getAllImportedHistories() {
        List<ImportedHistory> importedHistoryList = importedHistoryRepository.findAll();
        return importedHistoryList.stream().map(this::getImportedHistoryResponseDTO).toList();
    }
}
