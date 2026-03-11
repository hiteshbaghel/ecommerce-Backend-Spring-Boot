package com.ecom.project.controller;

import com.ecom.project.Service.CategoryService;
import com.ecom.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController( CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping ("/api/public/categories")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "Category added Successfully";
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);
           // return new ResponseEntity<>(status, HttpStatus.OK);
           // return ResponseEntity.ok(status);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}


