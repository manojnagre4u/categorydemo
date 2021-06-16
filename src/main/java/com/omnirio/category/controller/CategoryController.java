package com.omnirio.category.controller;

import com.omnirio.category.Repository.CategoryRepository;
import com.omnirio.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category categoryResponse = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

}
