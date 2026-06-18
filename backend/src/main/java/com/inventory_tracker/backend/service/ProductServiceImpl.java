package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.model.Category;
import com.inventory_tracker.backend.model.Product;
import com.inventory_tracker.backend.model.Supplier;
import com.inventory_tracker.backend.payload.CategoryDTO;
import com.inventory_tracker.backend.payload.ProductRequestDTO;
import com.inventory_tracker.backend.payload.ProductResponseDTO;
import com.inventory_tracker.backend.payload.SupplierDTO;
import com.inventory_tracker.backend.repositories.CategoryRepository;
import com.inventory_tracker.backend.repositories.ProductRepository;
import com.inventory_tracker.backend.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, SupplierRepository supplierRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Supplier supplier = supplierRepository.findById(productRequestDTO.getSupplierId()).orElse(null);
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null);
        Product product = modelMapper.map(productRequestDTO, Product.class);
        product.setCategory(category);
        product.setSupplier(supplier);
        Product savedProduct = productRepository.save(product);
        ProductResponseDTO responseProductDTO = modelMapper.map(savedProduct, ProductResponseDTO.class);
        responseProductDTO.setSupplier(modelMapper.map(supplier, SupplierDTO.class));
        responseProductDTO.setCategory(modelMapper.map(category, CategoryDTO.class));
        return responseProductDTO;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> productsList = productRepository.findAll();
        return productsList.stream().map(product -> {
            ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
            productResponseDTO.setSupplier(modelMapper.map(product.getSupplier(), SupplierDTO.class));
            productResponseDTO.setCategory(modelMapper.map(product.getCategory(), CategoryDTO.class));
            return productResponseDTO;
        }).collect(Collectors.toList());
    }
}
