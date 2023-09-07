package dev.example.productservice.service;

import dev.example.productservice.dtos.GenericProductDto;

public interface ProductService {
    public GenericProductDto getProductById(Long id);
}
