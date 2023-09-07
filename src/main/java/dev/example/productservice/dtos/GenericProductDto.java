package dev.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
