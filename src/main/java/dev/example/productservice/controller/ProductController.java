package dev.example.productservice.controller;

import dev.example.productservice.dtos.GenericProductDto;
import dev.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService ;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        //return new ArrayList<>(Arrays.asList(new GenericProductDto(),new GenericProductDto()));
        return productService.getAllProduct();
//
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {

        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById() {

    }

    @PostMapping
    public String createProduct() {
        return "Created new product with id : " + UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }
}