package dev.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order extends BaseModel{
    @ManyToMany
    @JoinTable(name="product_order",
            joinColumns = @JoinColumn(name="orderrr_id"),
            inverseJoinColumns = @JoinColumn(name = "producttt_id")
    )
    private List<Product> productList;
}
