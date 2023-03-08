package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.OrderDto;
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
    private final ProductService productService;
    private final OrderService orderService;
    private final AccountService accountService;

    public TableService(TableRepository repository, ProductService productService, OrderService orderService, AccountService accountService) {
        this.repository = repository;
        this.productService = productService;
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
        return TableDto.toDto(new Table());
    }

    public TableDto tableTakenOrder(Integer tableId, List<ProductRequest> productRequests){
        Table table = repository.findByIdAndIsAvailable(tableId, true).orElseThrow();
        List<Order> orderList = productRequests.stream().map(
                productRequest -> {
            Order order = OrderDto.productToOrder(productService.product(productRequest.productId()));
            order.setAmount(productRequest.amount());
            return order;
        }).toList();
        List<Order> savedOrders = orderService.createOrders(orderList);
        Account account = accountService.produceAccount(
                Account.builder()
                        .orders(savedOrders)
                        .build()
        );
        List<Account> accounts = table.getAccounts();
        accounts.add(account);
        return TableDto.toDto(repository.save(
                Table.builder()
                        .accounts(accounts)
                        .isAvailable(false)
                        .build()
        ));
    }
}
