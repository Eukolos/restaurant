package com.luinwee.restaurant.dto;

import com.luinwee.restaurant.model.Table;

import java.util.List;

public record TableStatusResponse(
        Integer id,
        Boolean isAvailable
) {
    public static TableStatusResponse toDto(Table table) {
        return new TableStatusResponse(
                table.getId(),
                table.getIsAvailable()
        );
    }

    public static List<TableStatusResponse> toDtoList(List<Table> tableList) {
        return tableList.stream().map(TableStatusResponse::toDto).toList();
    }

}