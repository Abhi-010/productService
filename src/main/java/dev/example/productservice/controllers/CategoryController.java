package dev.example.productservice.controllers;

import dev.example.productservice.dtos.GetProductTitlesRequestDto;
import dev.example.productservice.dtos.ProductDto;
import dev.example.productservice.models.Category;
import dev.example.productservice.models.Product;
import dev.example.productservice.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/{uuid}")
    public List<ProductDto> getCategory(@PathVariable("uuid") String uuid){

        Category category = categoryService.getCategory(uuid);
        List<Product> productList = category.getProductList();

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product p : productList){
            ProductDto productDto = new ProductDto();
            productDto.setDescription(p.getDescription());
            productDto.setPrice(p.getPrice());
            productDto.setTitle(p.getTitle());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @GetMapping("/titles/")
    public  List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto requestDto){
        List<String> uuids = requestDto.getUuids();
        return categoryService.getProductTitles1(uuids);
    }

}
