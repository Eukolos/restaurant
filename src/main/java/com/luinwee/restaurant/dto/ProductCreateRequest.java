package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.model.Category;
import com.luinwee.restaurant.model.Product;

import java.util.List;

public record ProductCreateRequest(
        String name,
        float price,
        float cost,
        Category category,
        int quantity
) {
    public static ProductCreateRequest toDto(Product product){
        return new ProductCreateRequest(
                product.getName(),
                product.getPrice(),
                product.getCost(),
                product.getCategory(),
                product.getQuantity()
        );
    }
    public static List<ProductCreateRequest> toDtoList(List<Product> productList){
        return productList.stream().map(ProductCreateRequest::toDto).toList();
    }
    public static Product toModel(ProductCreateRequest productDto){
        return Product.builder()
                .name(productDto.name)
                .price(productDto.price)
                .cost(productDto.cost)
                .category(productDto.category)
                .quantity(productDto.quantity)
                .build();
    }
    public static List<Product> toModelList(List<ProductCreateRequest> productDtoList){
        return productDtoList.stream().map(ProductCreateRequest::toModel).toList();
    }
}
