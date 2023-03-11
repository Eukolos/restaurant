package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.model.Account;
import com.luinwee.restaurant.model.Order;
import com.luinwee.restaurant.model.Table;
import com.luinwee.restaurant.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private final TableRepository repository;
    private final OrderService orderService;
    private final AccountService accountService;

    public TableService(TableRepository repository, OrderService orderService, AccountService accountService) {
        this.repository = repository;
        this.orderService = orderService;
        this.accountService = accountService;
    }

    public List<TableDto> getTableList() {
        return TableDto.toDtoList(repository.findAll());
    }

    public List<TableDto> getAvailableTableList() {
        return TableDto.toDtoList(repository.findByIsAvailable(true).orElseThrow());
    }

    public TableDto getTable(int tableId) {
        return TableDto.toDto(repository.findById(tableId).orElseThrow());
    }

    public TableDto tableTakenOrder(Integer tableId, List<ProductRequest> productRequests){

        Table table = repository.findByIdAndIsAvailable(tableId, true).orElseThrow();

        List<Order> savedOrders = orderService.saveProductsAsOrders(productRequests);

        Account account = accountService.produceAccount(
                Account.builder()
                        .totalPrice(getTotalPrice(savedOrders))
                        .isActive(true)
                        .orders(savedOrders)
                        .table(table)
                        .build()
        );
        List<Account> accounts = table.getAccounts();
        accounts.add(account);
        return TableDto.toDto(repository.save(
                Table.builder()
                        .id(table.getId())
                        .accounts(accounts)
                        .isAvailable(false)
                        .build()
        ));
    }

    public TableDto tableUpdateOrder (Integer tableId, List<ProductRequest> productRequests){
        Table table = repository.findByIdAndIsAvailable(tableId, false).orElseThrow();
        List<Order> savedOrders = orderService.saveProductsAsOrders(productRequests);
        Account account = table.getAccounts().stream()
                .filter(x -> x.getIsActive().equals(true))
                .findFirst().orElseThrow();
        List<Order> OrderList = account.getOrders();
        OrderList.addAll(savedOrders);
        accountService.updateAccount(Account.builder()
                .id(account.getId())
                .totalPrice(getTotalPrice(OrderList))
                .isActive(account.getIsActive())
                .orders(OrderList)
                .table(account.getTable())
                .build());
        return TableDto.toDto(table);
    }

    private static float getTotalPrice(List<Order> savedOrders) {
        float totalPrice = 0;
        for (Order savedOrder : savedOrders) {
            totalPrice = totalPrice + (savedOrder.getAmount() * savedOrder.getPrice());
        }
        return totalPrice;
    }
}
