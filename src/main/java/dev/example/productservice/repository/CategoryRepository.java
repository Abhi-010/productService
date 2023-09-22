package dev.example.productservice.repository;

import dev.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    Optional<Category> findById(UUID uuid);

    //List<Category> findAllByName(String name);

    //List<Category> findById(String uuid);

}
