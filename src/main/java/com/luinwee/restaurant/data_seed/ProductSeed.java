//package com.luinwee.restaurant.data_seed;
//
//import com.luinwee.restaurant.model.Category;
//import com.luinwee.restaurant.model.Product;
//import com.luinwee.restaurant.repository.ProductRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class ProductSeed implements CommandLineRunner {
//    private final ProductRepository pruductRepository;
//
//    public ProductSeed(ProductRepository pruductRepository) {
//        this.pruductRepository = pruductRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        Product p1 = Product.builder()
//                .name("Kebap")
//                .price(80)
//                .cost(50)
//                .category(Category.FAST_FOOD)
//                .build();
//        Product p2 = Product.builder()
//                .name("Hamburger")
//                .price(60)
//                .cost(40)
//                .category(Category.FAST_FOOD)
//                .build();
//        Product p3 = Product.builder()
//                .name("Tavuk Izgara")
//                .price(80)
//                .cost(50)
//                .category(Category.FAST_FOOD)
//                .build();
//        Product p4 = Product.builder()
//                .name("Döner")
//                .price(50)
//                .cost(30)
//                .category(Category.FAST_FOOD)
//                .build();
//        Product p5 = Product.builder()
//                .name("Yumurta")
//                .price(30)
//                .cost(15)
//                .category(Category.BREAKFAST)
//                .build();
//        Product p6 = Product.builder()
//                .name("Tost")
//                .price(40)
//                .cost(20)
//                .category(Category.BREAKFAST)
//                .build();
//        Product p7 = Product.builder()
//                .name("Menemen")
//                .price(45)
//                .cost(25)
//                .category(Category.BREAKFAST)
//                .build();
//        Product p8 = Product.builder()
//                .name("Baklava")
//                .price(80)
//                .cost(50)
//                .category(Category.DESSERT)
//                .build();
//        Product p9 = Product.builder()
//                .name("Sufle")
//                .price(50)
//                .cost(30)
//                .category(Category.DESSERT)
//                .build();
//        Product p10 = Product.builder()
//                .name("Sütlaç")
//                .price(50)
//                .cost(30)
//                .category(Category.DESSERT)
//                .build();
//        Product p11 = Product.builder()
//                .name("Kadayıf")
//                .price(60)
//                .cost(40)
//                .category(Category.DESSERT)
//                .build();
//        Product p12 = Product.builder()
//                .name("Çay")
//                .price(15)
//                .cost(5)
//                .category(Category.BEVERAGE)
//                .build();
//        Product p13 = Product.builder()
//                .name("Kola")
//                .price(25)
//                .cost(20)
//                .category(Category.BEVERAGE)
//                .build();
//        Product p14 = Product.builder()
//                .name("Fanta")
//                .price(25)
//                .cost(20)
//                .category(Category.BEVERAGE)
//                .build();
//        Product p15 = Product.builder()
//                .name("Ayran")
//                .price(20)
//                .cost(15)
//                .category(Category.BEVERAGE)
//                .build();
//        pruductRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));
//    }
//}
