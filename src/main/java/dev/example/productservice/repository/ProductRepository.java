package dev.example.productservice.repository;

import dev.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findAllById(UUID id);

    //List<Product> findAllById(String name);

    @Query(value = "select * from product join product_order on" +
            " product.id = product_order.producttt_id" +
            " where title = :title", nativeQuery = true)
    List<Product> findAllByTitle(String title);


//    List<Product> findAll(String tittle);

}
