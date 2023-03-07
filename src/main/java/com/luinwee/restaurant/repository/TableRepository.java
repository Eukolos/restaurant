package com.luinwee.restaurant.repository;

import com.luinwee.restaurant.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
