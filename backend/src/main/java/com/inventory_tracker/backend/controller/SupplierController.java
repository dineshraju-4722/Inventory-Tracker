package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.payload.SupplierDTO;
import com.inventory_tracker.backend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDTO> addSupplier(@RequestBody SupplierDTO supplierDTO){
        SupplierDTO responseSupplierDTO = supplierService.addSupplier(supplierDTO );
        return new ResponseEntity<>(responseSupplierDTO, HttpStatus.CREATED);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllProducts(){
        List<SupplierDTO> supplierDTOs = supplierService.getAllSuppliers();
        return new ResponseEntity<>(supplierDTOs,HttpStatus.OK);
    }
}
