package com.luinwee.restaurant.controller;

import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.service.TableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/table")
@RestController
public class TableController {
    private final TableService service;

    public TableController(TableService service) {
        this.service = service;
    }

    @GetMapping
    public List<TableDto> getTableList() {
        return service.getTableList();
    }

    @GetMapping("/available")
    public List<TableDto> getAvailableTableList() {
        return service.getAvailableTableList();
    }

    @GetMapping("/{tableId}")
    public TableDto getTable(@PathVariable int tableId) {
        return service.getTable(tableId);
    }

    @PostMapping("/{tableId}")
    public TableDto tableTakenOrder(
            @PathVariable int tableId,
            @RequestBody List<ProductRequest> productRequests
    ){
        return service.tableTakenOrder(tableId, productRequests);
    }
    @PutMapping("/{tableId}")
    public TableDto tableUpdateOrder(
            @PathVariable int tableId,
            @RequestBody List<ProductRequest> productRequests
    ){
        return service.tableUpdateOrder(tableId, productRequests);
    }

}
