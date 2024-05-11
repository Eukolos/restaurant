package com.luinwee.restaurant.controller;

import com.luinwee.restaurant.dto.ProductCreateRequest;
import com.luinwee.restaurant.dto.ProductDto;
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
    public List<ProductDto> getProductList(){
        return service.getProductList();
    }
    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable Long productId){
        return service.getProduct(productId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductCreateRequest productDto){
        return service.createProduct(productDto);
    }
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return service.updateProduct(productDto);
    }
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId){
        service.deleteProduct(productId);
    }
}
