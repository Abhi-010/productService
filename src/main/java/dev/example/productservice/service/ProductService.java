package dev.example.productservice.service;

import dev.example.productservice.dtos.GenericProductDto;
import dev.example.productservice.exception.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id);
    public List<GenericProductDto> getAllProduct();
    public GenericProductDto deleteProductById(Long id) throws NotFoundException;
}
