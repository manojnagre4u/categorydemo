package com.omnirio.category.controller;

import com.omnirio.category.Exception.ResourceNotFoundException;
import com.omnirio.category.Repository.CategoryRepository;
import com.omnirio.category.Repository.ProductRepository;
import com.omnirio.category.dto.ProductDto;
import com.omnirio.category.model.Product;
import com.omnirio.category.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("category/{categoryId}/product")
    public ResponseEntity<Object> save(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Product product) throws ResourceNotFoundException {
        long productId = categoryRepository.findById(categoryId).map(category -> {
            product.setCategory(category);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("category not found :: " + categoryId)).getProductId();

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.getById(productId));

    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value = "productId") Long productId)
            throws ResourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));

    }
}
