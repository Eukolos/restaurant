package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.model.Category;
import com.luinwee.restaurant.model.Order;
import com.luinwee.restaurant.model.Product;

import java.util.List;

public record OrderDto(
        Long id,
        String name,
        float amount,
        float price,
        float cost,
        Category category
) {
    public static OrderDto toDto(Order order){
        return new OrderDto(
                order.getId(),
                order.getName(),
                order.getAmount(),
                order.getPrice(),
                order.getCost(),
                order.getCategory()
        );
    }
    public static List<OrderDto> toDtoList(List<Order> OrderList){
        return OrderList.stream().map(OrderDto::toDto).toList();
    }
    public static Order toModel(OrderDto orderDto){
        return Order.builder()
                .id(orderDto.id)
                .name(orderDto.name)
                .amount(orderDto.amount)
                .price(orderDto.price)
                .cost(orderDto.cost)
                .category(orderDto.category)
                .build();
    }
    public static List<Order> toModelList(List<OrderDto> orderDtoList){
        return orderDtoList.stream().map(OrderDto::toModel).toList();
    }

    public static Order productToOrder(Product product){
        return Order.builder()
                .name(product.getName())
                .amount(0)
                .price(product.getPrice())
                .cost(product.getCost())
                .category(product.getCategory())
                .build();
    }
}
