package dev.example.productservice.services;

import dev.example.productservice.models.Category;

public interface CategoryService {
    Category getCategory(String uuid);
}
