package com.example.lab7.mapper;

import com.example.lab7.dto.CategoryDto;
import com.example.lab7.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> categories);
    Set<CategoryDto> toDtoSet(Set<Category> categories);
}