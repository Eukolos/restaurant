package com.luinwee.restaurant.data_seed;

import com.luinwee.restaurant.model.Table;
import com.luinwee.restaurant.repository.TableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class TableSeed implements CommandLineRunner {
    private final TableRepository repository;

    public TableSeed(TableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
            int tableNumber = 1;
            Table table = Table.builder()
                    .id(++tableNumber)
                    .accounts(new ArrayList<>())
                    .isAvailable(false)
                    .build();
            repository.save(table);
        }
    }
}
