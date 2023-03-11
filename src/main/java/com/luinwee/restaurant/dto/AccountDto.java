package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.model.Account;

import java.time.LocalDateTime;
import java.util.List;

public record AccountDto(
        Long id,
        float totalPrice,
        boolean isActive,
        List<OrderDto> orders
) {
    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getTotalPrice(),
                account.getIsActive(),
                OrderDto.toDtoList(account.getOrders())
        );
    }

    public static List<AccountDto> toDtoList(List<Account> accountList) {
        return accountList.stream().map(AccountDto::toDto).toList();
    }

    public static Account toModel(AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.id)
                .totalPrice(accountDto.totalPrice)
                .isActive(accountDto.isActive)
                .orders(OrderDto.toModelList(accountDto.orders))
                .build();
    }

    public static List<Account> toModelList(List<AccountDto> accountDtoList) {
        return accountDtoList.stream().map(AccountDto::toModel).toList();
    }
}