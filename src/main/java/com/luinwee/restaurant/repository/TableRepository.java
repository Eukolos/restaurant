package com.luinwee.restaurant.repository;

import com.luinwee.restaurant.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    Optional<List<Table>> findByIsAvailable(boolean isAvailable);
    Optional<Table> findByIdAndIsAvailable(Integer tableId, boolean isAvailable);
}
