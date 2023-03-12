package com.luinwee.restaurant.controller;

import com.luinwee.restaurant.dto.OrderDto;
import com.luinwee.restaurant.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrderInTable(@PathVariable Long orderId){
        service.deleteOrder(orderId);
    }


    @GetMapping
    public List<OrderDto> getAll(){
        return service.getOrderList();
    }

}
