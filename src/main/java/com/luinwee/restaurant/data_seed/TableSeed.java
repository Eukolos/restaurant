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

        for (int i = 0; i < 20; i++) {
            Table table = new Table(true);
            repository.save(table);
        }
        ProductRequest p1 = new ProductRequest(
                1L,
                2
        );

        ProductRequest productRequest7 = new ProductRequest(9L,2);
        service.tableTakenOrder(3, List.of(productRequest7));

        ProductRequest productRequest1 = new ProductRequest(2L,5);
        ProductRequest productRequest2 = new ProductRequest(7L,3);
        ProductRequest productRequest3 = new ProductRequest(9L,1);
        service.tableTakenOrder(1, List.of(productRequest1, productRequest2, productRequest3));
        ProductRequest productRequest4 = new ProductRequest(1L,3);
        ProductRequest productRequest5 = new ProductRequest(2L,1);
        ProductRequest productRequest6 = new ProductRequest(5L,2);
        service.tableTakenOrder(9, List.of(productRequest6, productRequest5, productRequest4));

      //  TableDto tableDto = service.tableTakenOrder(1, List.of(p1));
      //  log.info(tableDto.toString());
    }
}
