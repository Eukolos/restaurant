package com.luinwee.restaurant.controller;

import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.service.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/table")
@RestController
public class TableController {
    private final TableService service;

    public TableController(TableService service) {
        this.service = service;
    }

    @GetMapping
    public List<TableDto> getTableList(){
        return service.getTableList();
    }
    @GetMapping("/available")
    public List<TableDto> getAvailableTableList(){
        return service.getAvailableTableList();
    }
    @GetMapping("/{tableId}")
    public TableDto getTable (@PathVariable int tableId) {
        return service.getTable(tableId);
    }


}
