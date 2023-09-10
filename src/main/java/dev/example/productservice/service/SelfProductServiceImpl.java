package dev.example.productservice.service;

import dev.example.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        return null;
    }
}
