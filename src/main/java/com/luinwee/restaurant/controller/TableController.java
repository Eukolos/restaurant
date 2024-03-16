package com.luinwee.restaurant.controller;

import com.luinwee.restaurant.dto.AccountDto;
import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.dto.TableStatusResponse;
import com.luinwee.restaurant.model.Account;
import com.luinwee.restaurant.service.AccountService;
import com.luinwee.restaurant.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/table")
@RestController
public class TableController {
    private final TableService service;
    private final AccountService accountService;

    public TableController(TableService service, AccountService accountService) {
        this.service = service;
        this.accountService = accountService;
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

    @GetMapping("/status")
    public List<TableStatusResponse> getTableStatusList() {
        return service.getTableStatusList();
    }

    @GetMapping("/active-account/{tableId}")
    public AccountDto getActiveAccount(@PathVariable int tableId){
        return accountService.getAccountByTableIdAndIsActive((long) tableId, true);
    }

    @PostMapping("/{tableId}")
    public TableDto tableTakenOrder(
            @PathVariable int tableId,
            @RequestBody List<ProductRequest> productRequests
    ){
        log.info("tableId: {}", tableId);
        log.info("productRequests: {}", productRequests);

        return service.tableTakenOrder(tableId, productRequests);
    }
    @PutMapping("/{tableId}")
    public TableDto tableUpdateOrder(
            @PathVariable int tableId,
            @RequestBody List<ProductRequest> productRequests
    ){
        return service.tableUpdateOrder(tableId, productRequests);
    }
    @DeleteMapping("/{tableId}")
    public void accountInactiveInTable(@PathVariable int tableId){
        service.accountInactiveInTable(tableId);
    }



}