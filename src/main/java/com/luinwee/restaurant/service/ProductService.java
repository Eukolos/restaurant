package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.ProductDto;
import com.luinwee.restaurant.model.Product;
import com.luinwee.restaurant.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getProductList(){
        return ProductDto.toDtoList(repository.findAll());
    }
    public ProductDto getProduct(Long productId){
        return ProductDto.toDto(repository.findById(productId).orElseThrow(null));
    }
    protected Product product(Long productId){
        return repository.findById(productId).orElseThrow(null);
    }
    public ProductDto createProduct(ProductDto productDto){
        return ProductDto.toDto(repository.save(ProductDto.toModel(productDto)));
    }
    public ProductDto updateProduct(ProductDto productDto){
        Product product = repository.findById(productDto.id()).orElseThrow(null);
        return ProductDto.toDto(repository.save(
                new Product(
                        product.getId(),
                        productDto.name(),
                        productDto.price(),
                        productDto.cost(),
                        productDto.category()
                )
        ));
    }
    public void deleteProduct(Long productId){
        repository.deleteById(productId);
    }
}
