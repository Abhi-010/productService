package dev.example.productservice.service;

import dev.example.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id);
    public List<GenericProductDto> getAllProduct();
}
