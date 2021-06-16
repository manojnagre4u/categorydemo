package com.omnirio.category.service;

import com.omnirio.category.Exception.ResourceNotFoundException;
import com.omnirio.category.dto.ProductDto;

public interface ProductService {
    ProductDto getProduct(Long productId) throws ResourceNotFoundException;
}
