package dev.example.productservice.repository;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE = "select * from product join product_order on" +
            " product.id = product_order.producttt_id" +
            " where title = :title";
}
