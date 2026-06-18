package com.inventory_tracker.backend.service;

import com.inventory_tracker.backend.payload.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService   {
    public CategoryDTO addCategory(CategoryDTO categoryDTO) ;

    List<CategoryDTO> getAllCategories();
}
