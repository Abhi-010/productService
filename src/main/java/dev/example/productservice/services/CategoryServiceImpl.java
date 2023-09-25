package dev.example.productservice.services;

import dev.example.productservice.models.Category;
import dev.example.productservice.models.Product;
import dev.example.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository ;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategory(String uuid) {
        Optional<Category> categoryOptional =categoryRepository.findById(UUID.fromString(uuid));

        if(categoryOptional.isEmpty()){
            return null;
        }
        Category category = categoryOptional.get();
        List<Product> productList = category.getProductList();

        return category;
    }

    public List<String> getProductTitles(String uuid){
        Optional<Category> categoryOptional =categoryRepository.findById(UUID.fromString(uuid));

        if(categoryOptional.isEmpty()){
            return null;
        }
        Category category = categoryOptional.get();
        List<String> titles = new ArrayList<>();


        List<Product> productList = category.getProductList();

        for(Product p : productList){
            titles.add(p.getTitle());
        }

        return titles;
    }

    public List<String> getProductTitles1(List<String> categoryUUIDs){
        List<UUID> uuids = new ArrayList<>();
        for(String s : categoryUUIDs){
            uuids.add(UUID.fromString(s));
        }
        List<Category> categories = categoryRepository.findAllById(uuids);

        List<String> titles = new ArrayList<>();

        for(Category c : categories){
            List<Product> productList = c.getProductList();
            for(Product p : productList){
                titles.add(p.getTitle());
            }
        }
        return titles;
    }
}
