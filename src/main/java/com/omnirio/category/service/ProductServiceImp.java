package com.omnirio.category.service;

import com.omnirio.category.Exception.ResourceNotFoundException;
import com.omnirio.category.Repository.CategoryRepository;
import com.omnirio.category.Repository.ProductRepository;
import com.omnirio.category.dto.CategoryAttributesDto;
import com.omnirio.category.dto.ProductDto;
import com.omnirio.category.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto getProduct(Long productId) throws ResourceNotFoundException {

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            List<CategoryAttributesDto> categoryAttributesDtos = new ArrayList<>();
            product.getCategory().getCategoryAttributes().forEach(categoryAttributes -> {
                categoryAttributesDtos.add(CategoryAttributesDto.builder().attributeName(categoryAttributes.getAttributeName()).attributeValue(categoryAttributes.getAttributeValue()).build());
            });
            ProductDto productDto = ProductDto.builder().productId(productId).productName(product.getProductName()).categoryAttributes(categoryAttributesDtos)
                    .categoryId(product.getCategory().getCategoryId())
                    .categoryName(product.getCategory().getCategoryName()).build();

            return productDto;
        } else
            throw new ResourceNotFoundException("Product not found :: " + productId);

    }
}
