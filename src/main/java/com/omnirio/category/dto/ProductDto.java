package com.omnirio.category.dto;

import com.omnirio.category.model.CategoryAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
    private List<CategoryAttributesDto> categoryAttributes;
    private String productName;
    private Long productId;
    private Long categoryId;
    private String categoryName;
}
