package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private final TableRepository repository;

    public TableService(TableRepository repository) {
        this.repository = repository;
    }


    public List<TableDto> getTableList() {
        return TableDto.toDtoList(repository.findAll());
    }

    public List<TableDto> getAvailableTableList() {
        return TableDto.toDtoList(repository.findByIsAvailable(true).orElseThrow());
    }

    public TableDto getTable(int tableId) {
    }
}
