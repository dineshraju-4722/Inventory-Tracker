package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.payload.ProductRequestDTO;
import com.inventory_tracker.backend.payload.ProductResponseDTO;
import com.inventory_tracker.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO responseProductDTO = productService.addProduct(productRequestDTO);
        return  new ResponseEntity<>(responseProductDTO, HttpStatus.CREATED);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productsDTOs = productService.getAllProducts();
        return new ResponseEntity<>(productsDTOs,HttpStatus.OK);
    }


}
