package com.inventory_tracker.backend.controller;

import com.inventory_tracker.backend.payload.CategoryDTO;
import com.inventory_tracker.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryControler {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
         List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();
         return new ResponseEntity<>(categoryDTOList,HttpStatus.OK);
    }
}
