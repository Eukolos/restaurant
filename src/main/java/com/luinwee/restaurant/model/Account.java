package com.luinwee.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalPrice;
    private boolean isActive;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private Table table;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Account(Long id, float totalPrice, boolean isActive, List<Order> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.isActive = isActive;
        this.orders = orders;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
