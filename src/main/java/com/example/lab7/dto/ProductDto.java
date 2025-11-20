package com.example.lab7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer quantity;
    private String description;
    private String brand;

    private List<ReviewDto> reviews;

    private Set<CategoryDto> categories;
}