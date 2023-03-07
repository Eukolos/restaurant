package com.luinwee.restaurant.repository;


import com.luinwee.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
