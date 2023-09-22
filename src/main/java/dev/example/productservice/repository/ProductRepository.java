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

    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String title);

//    @Query("select Product.description from Product where " +
//            "Product.price.currency = :currency and " +
//            "Product.title = :title")
//    List<Product> readAllByTitle(String currency, String title);

//    @Query("select Product from Product where " +
//            "Product.price.currency = :currency and " +
//            "Product.title = :title")
//    List<Product> readAllByCurrencyAndTitle(String currency, String title);


//    List<Product> findAll(String tittle);

}
