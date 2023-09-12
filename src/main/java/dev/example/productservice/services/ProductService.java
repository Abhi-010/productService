package dev.example.productservice.services;

import dev.example.productservice.dtos.GenericProductDto;
import dev.example.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id);
    public List<GenericProductDto> getAllProduct();
    public GenericProductDto deleteProductById(Long id) throws NotFoundException;
}
