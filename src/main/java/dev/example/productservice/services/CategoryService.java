package dev.example.productservice.services;

import dev.example.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(String uuid);
    public List<String> getProductTitles(String uuid);
    List<String> getProductTitles1(List<String> categoryUUIDs);
}
