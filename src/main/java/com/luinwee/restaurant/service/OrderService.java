package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.OrderDto;
import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.model.Order;
import com.luinwee.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final ProductService productService;

    public OrderService(OrderRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public List<OrderDto> getOrderList(){
        return OrderDto.toDtoList(repository.findAll());
    }
    public OrderDto getOrder(Long orderId){
        return OrderDto.toDto(repository.findById(orderId).orElseThrow(null));
    }
    public OrderDto createOrder(OrderDto orderDto){
        return OrderDto.toDto(repository.save(OrderDto.toModel(orderDto)));
    }
    protected List<Order> createOrders(List<Order> orders){
        return repository.saveAll(orders);
    }
    public OrderDto updateOrder(OrderDto orderDto){
        Order order = repository.findById(orderDto.id()).orElseThrow(null);
        return OrderDto.toDto(repository.save(
                new Order(
                        order.getId(),
                        orderDto.name(),
                        orderDto.amount(),
                        orderDto.price(),
                        orderDto.cost(),
                        orderDto.category()
                )
        ));
    }
    public List<Order> saveProductsAsOrders(List<ProductRequest> productRequests){
        List<Order> orderList = productRequests.stream().map(
                productRequest -> {
                    Order order = OrderDto.productToOrder(productService.product(productRequest.productId()));
                    order.setAmount(productRequest.amount());
                    return order;
                }).toList();
        return repository.saveAll(orderList);
    }
    public void deleteOrder(Long orderId){
        repository.deleteById(orderId);
    }
}
