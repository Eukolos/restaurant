package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.dto.TableStatusResponse;
import com.luinwee.restaurant.model.Account;
import com.luinwee.restaurant.model.Order;
import com.luinwee.restaurant.model.Table;
import com.luinwee.restaurant.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<TableStatusResponse> getTableStatusList() {
        return TableStatusResponse.toDtoList(repository.findAll());
    }

    public TableDto getTable(int tableId) {
        return TableDto.toDto(repository.findById(tableId).orElseThrow());
    }

    public TableDto tableTakenOrder(Integer tableId, List<ProductRequest> productRequests) {

        Table table = repository.findById(tableId).orElseThrow();
        if (table.getIsAvailable()) {
            Account newAccount = accountService.produceAccount(new Account());

            List<Order> savedOrders = orderService.saveProductsAsOrders(productRequests, newAccount);

            Account account = accountService.produceAccount(
                    Account.builder()
                            .id(newAccount.getId())
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
        Account activeAccount = table.getAccounts().stream()
                .filter(x -> x.getIsActive().equals(true))
                .findFirst().orElseThrow();

        List<Order> savedOrders = orderService.saveProductsAsOrders(productRequests, activeAccount);
        List<Order> OrderList = activeAccount.getOrders();
        OrderList.addAll(savedOrders);
        accountService.updateAccount(Account.builder()
                .id(activeAccount.getId())
                .totalPrice(getTotalPrice(OrderList))
                .isActive(activeAccount.getIsActive())
                .orders(OrderList)
                .table(activeAccount.getTable())
                .build());


        return TableDto.toDto(repository.findById(tableId).orElseThrow());


    }

    public TableDto tableUpdateOrder(Integer tableId, List<ProductRequest> productRequests) {
        Table table = repository.findByIdAndIsAvailable(tableId, false).orElseThrow();
        Account account = table.getAccounts().stream()
                .filter(x -> x.getIsActive().equals(true))
                .findFirst().orElseThrow();
        List<Order> savedOrders = orderService.saveProductsAsOrders(productRequests, account);
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

    public void accountInactiveInTable(int tableId) {
        Table table = repository.findByIdAndIsAvailable(tableId, false).orElseThrow();
        Account account = table.getAccounts().stream()
                .filter(x -> x.getIsActive().equals(true))
                .findFirst().orElseThrow();
        accountService.updateAccount(Account.builder()
                .id(account.getId())
                .totalPrice(account.getTotalPrice())
                .isActive(false)
                .orders(account.getOrders())
                .table(account.getTable())
                .build());
        repository.save(
                new Table(
                        table.getId(),
                        table.getAccounts(),
                        true,
                        ""
                )
        );
    }

    public void tableIncreaseOrDecrease(int tableQuantity) {
        int tableCount = repository.findAll().size();
        if (tableQuantity > tableCount) {
            for (int i = 0; i < tableQuantity - tableCount; i++) {
                repository.save(new Table(true));
            }
        } else if (tableQuantity < tableCount) {
            List<Table> tables = repository.findAll();
            int tablesToDelete = tableCount - tableQuantity;
            for (int i = tables.size() - 1; i >= tables.size() - tablesToDelete; i--) {
                repository.delete(tables.get(i));
            }
        }

    }
}
