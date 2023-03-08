package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.model.Account;

import java.time.LocalDateTime;
import java.util.List;

public record AccountDto(
        Long id,
        float totalPrice,
        List<OrderDto> orders,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getTotalPrice(),
                OrderDto.toDtoList(account.getOrders()),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    public static List<AccountDto> toDtoList(List<Account> accountList) {
        return accountList.stream().map(AccountDto::toDto).toList();
    }

    public static Account toModel(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.id)
                .totalPrice(accountDto.totalPrice)
                .orders(OrderDto.toModelList(accountDto.orders))
                .createdAt(accountDto.createdAt)
                .updatedAt(accountDto.updatedAt)
                .build();
    }

    public static List<Account> toModelList(List<AccountDto> accountDtoList) {
        return accountDtoList.stream().map(AccountDto::toModel).toList();
    }
}