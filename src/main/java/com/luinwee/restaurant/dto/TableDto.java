package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.dto.AccountDto;
import com.luinwee.restaurant.model.Table;
import com.luinwee.restaurant.model.Table;

import java.io.Serializable;
import java.util.List;


public record TableDto(
        Integer id,
        List<AccountDto> accounts,
        Boolean isAvailable
)  {
    public static TableDto toDto(Table table){
        return new TableDto(
                table.getId(),
                AccountDto.toDtoList(table.getAccounts()),
                table.getIsAvailable()
        );
    }
    public static List<TableDto> toDtoList(List<Table> tableList){
        return tableList.stream().map(TableDto::toDto).toList();
    }
    public static Table toModel(TableDto tableDto){
        return Table.builder()
                .id(tableDto.id)
                .accounts(AccountDto.toModelList(tableDto.accounts))
                .isAvailable(tableDto.isAvailable)
                .build();
    }
    public static List<Table> toModelList(List<TableDto> tableDtoList){
        return tableDtoList.stream().map(TableDto::toModel).toList();
    }

}