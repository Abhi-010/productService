package dev.example.productservice.service;

import dev.example.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

@Service
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }
}
