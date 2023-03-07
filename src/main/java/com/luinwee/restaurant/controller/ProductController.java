package com.luinwee.restaurant.controller;

import com.luinwee.restaurant.dto.ProductDto;
import com.luinwee.restaurant.model.Product;
import com.luinwee.restaurant.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping
    public List<ProductDto> productDtoList(){
        return service.productDtoList();
    }
    @GetMapping("{productId}")
    public ProductDto productDto(@PathVariable Long productId){
        return service.productDto(productId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return service.createProduct(productDto);
    }
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return service.updateProduct(productDto);
    }
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(Long productId){
        service.deleteProduct(productId);
    }
}
