package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.model.Supplier;
import com.inventory_tracker.backend.payload.SupplierDTO;
import com.inventory_tracker.backend.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements  SupplierService{


    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierDTO addSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        Supplier saved = supplierRepository.save(supplier);
        return modelMapper.map(saved, SupplierDTO.class);
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList.stream().map(supplier -> modelMapper.map(supplier, SupplierDTO.class)).toList();
    }
}
