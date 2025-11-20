package com.example.lab7.service;

import com.example.lab7.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    void deleteCategory(Long id);
}