package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.model.Category;
import com.luinwee.restaurant.model.Product;

import java.util.List;

public record ProductDto(
        Long id,
        String name,
        float price,
        float cost,
        Category category
) {
    public static ProductDto toDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCost(),
                product.getCategory()
        );
    }
    public static List<ProductDto> toDtoList(List<Product> productList){
        return productList.stream().map(ProductDto::toDto).toList();
    }
    public static Product toModel(ProductDto productDto){
        return Product.builder()
                .id(productDto.id)
                .name(productDto.name)
                .price(productDto.price)
                .cost(productDto.cost)
                .category(productDto.category)
                .build();
    }
}
