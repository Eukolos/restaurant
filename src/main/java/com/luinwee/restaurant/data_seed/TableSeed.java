package com.luinwee.restaurant.data_seed;

import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.dto.TableDto;
import com.luinwee.restaurant.model.Table;
import com.luinwee.restaurant.repository.TableRepository;
import com.luinwee.restaurant.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TableSeed implements CommandLineRunner {
    private final TableRepository repository;
    private final TableService service;


    public TableSeed(TableRepository repository, TableService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void run(String... args) {

        for (int i = 0; i < 11; i++) {
            Table table = new Table(true);
            repository.save(table);
        }
        ProductRequest p1 = new ProductRequest(
                1L,
                2
        );
        TableDto tableDto = service.tableTakenOrder(1, List.of(p1));
        log.info(tableDto.toString());
    }
}
