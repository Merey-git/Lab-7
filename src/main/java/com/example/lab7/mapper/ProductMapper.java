package com.example.lab7.mapper;

import com.example.lab7.dto.ProductDto;
import com.example.lab7.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class, CategoryMapper.class})
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto dto);

    List<ProductDto> toDtoList(List<Product> products);
}