package com.luinwee.restaurant.data_seed;

import com.luinwee.restaurant.dto.ProductRequest;
import com.luinwee.restaurant.model.Category;
import com.luinwee.restaurant.model.Product;
import com.luinwee.restaurant.model.Table;
import com.luinwee.restaurant.repository.ProductRepository;
import com.luinwee.restaurant.repository.TableRepository;
import com.luinwee.restaurant.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class TableSeed implements CommandLineRunner {
    private final TableRepository repository;
    private final TableService service;

    private final ProductRepository pruductRepository;


    public TableSeed(TableRepository repository, TableService service, ProductRepository pruductRepository) {
        this.repository = repository;
        this.service = service;
        this.pruductRepository = pruductRepository;
    }

    @Override
    public void run(String... args) {

        // add products

        Product p1 = Product.builder()
                .name("Kebap")
                .price(80)
                .cost(50)
                .category(Category.FAST_FOOD)
                .quantity(100)
                .build();
        Product p2 = Product.builder()
                .name("Hamburger")
                .price(60)
                .cost(40)
                .category(Category.FAST_FOOD)
                .quantity(50)
                .build();
        Product p3 = Product.builder()
                .name("Tavuk Izgara")
                .price(80)
                .cost(50)
                .category(Category.FAST_FOOD)
                .quantity(35)
                .build();
        Product p4 = Product.builder()
                .name("Döner")
                .price(50)
                .cost(30)
                .category(Category.FAST_FOOD)
                .quantity(22)
                .build();
        Product p5 = Product.builder()
                .name("Yumurta")
                .price(30)
                .cost(15)
                .category(Category.BREAKFAST)
                .quantity(45)
                .build();
        Product p6 = Product.builder()
                .name("Tost")
                .price(40)
                .cost(20)
                .category(Category.BREAKFAST)
                .quantity(30)
                .build();
        Product p7 = Product.builder()
                .name("Menemen")
                .price(45)
                .cost(25)
                .category(Category.BREAKFAST)
                .quantity(30)
                .build();
        Product p8 = Product.builder()
                .name("Baklava")
                .price(80)
                .cost(50)
                .category(Category.DESSERT)
                .quantity(30)
                .build();
        Product p9 = Product.builder()
                .name("Sufle")
                .price(50)
                .cost(30)
                .category(Category.DESSERT)
                .quantity(5)
                .build();
        Product p10 = Product.builder()
                .name("Sütlaç")
                .price(50)
                .cost(30)
                .category(Category.DESSERT)
                .quantity(20)
                .build();
        Product p11 = Product.builder()
                .name("Kadayıf")
                .price(60)
                .cost(40)
                .category(Category.DESSERT)
                .quantity(12)
                .build();
        Product p12 = Product.builder()
                .name("Çay")
                .price(15)
                .cost(5)
                .category(Category.BEVERAGE)
                .quantity(23)
                .build();
        Product p13 = Product.builder()
                .name("Kola")
                .price(25)
                .cost(20)
                .category(Category.BEVERAGE)
                .quantity(45)
                .build();
        Product p14 = Product.builder()
                .name("Fanta")
                .price(25)
                .cost(20)
                .category(Category.BEVERAGE)
                .quantity(12)
                .build();
        Product p15 = Product.builder()
                .name("Ayran")
                .price(20)
                .cost(15)
                .category(Category.BEVERAGE)
                .quantity(22)
                .build();
        pruductRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));


        // add tables

        for (int i = 0; i < 20; i++) {
            Table table = new Table(true);
            repository.save(table);
        }

        // add orders

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
