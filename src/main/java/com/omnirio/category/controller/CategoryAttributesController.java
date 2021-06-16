package com.omnirio.category.controller;

import com.omnirio.category.Exception.ResourceNotFoundException;
import com.omnirio.category.Repository.CategoryAttributesRepository;
import com.omnirio.category.Repository.CategoryRepository;
import com.omnirio.category.Repository.ProductRepository;
import com.omnirio.category.model.CategoryAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
@RestController
@RequestMapping("/api/attributes/")
public class CategoryAttributesController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryAttributesRepository categoryAttributesRepository;

    @PostMapping("category/{categoryId}/")
    public ResponseEntity<Object> save(@PathVariable(value = "categoryId") Long categoryId, @RequestBody CategoryAttributes categoryAttributes) throws ResourceNotFoundException {
        long attributeId = categoryRepository.findById(categoryId).map(category -> {
            categoryAttributes.setCategory(category);
            return categoryAttributesRepository.save(categoryAttributes);
        }).orElseThrow(() -> new ResourceNotFoundException("category not found :: " + categoryId)).getAttributeId();

        return ResponseEntity.status(HttpStatus.OK).body(categoryAttributesRepository.getById(attributeId));

    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Set<CategoryAttributes>> getCategoryAttributes(@PathVariable(value = "categoryId") Long categoryId) {
        Optional<Set<CategoryAttributes>> categoryAttributes = categoryRepository.findById(categoryId).map(category -> {
            return category.getCategoryAttributes();
        });
        if (categoryAttributes.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryAttributes.get());
        } else
            new ResourceNotFoundException("category not found :: " + categoryId);
        return null;
    }
}

