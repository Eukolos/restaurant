package com.luinwee.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float amount;
    private float price;
    private float cost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Order(Long id, String name, float amount, float price, float cost, Category category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.cost = cost;
        this.category = category;
    }
}
