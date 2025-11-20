package com.example.lab7.service.impl;

import com.example.lab7.dto.ProductDto;
import com.example.lab7.entity.Category;
import com.example.lab7.entity.Product;
import com.example.lab7.mapper.ProductMapper;
import com.example.lab7.repository.CategoryRepository;
import com.example.lab7.repository.ProductRepository;
import com.example.lab7.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);

        if (productDto.getCategories() != null && !productDto.getCategories().isEmpty()) {
            Set<Category> categories = new HashSet<>();

            productDto.getCategories().forEach(categoryDto -> {
                Category category = categoryRepository.findById(categoryDto.getId())
                        .orElseThrow(() -> new RuntimeException("Not found: " + categoryDto.getId()));
                categories.add(category);
            });

            product.setCategories(categories);
        }

        Product saved = productRepository.save(product);

        return productMapper.toDto(saved);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));

        existing.setName(productDto.getName());
        existing.setCategory(productDto.getCategory());
        existing.setPrice(productDto.getPrice());
        existing.setQuantity(productDto.getQuantity());
        existing.setDescription(productDto.getDescription());
        existing.setBrand(productDto.getBrand());

        if (productDto.getCategories() != null) {
            Set<Category> categories = new HashSet<>();
            productDto.getCategories().forEach(categoryDto -> {
                Category category = categoryRepository.findById(categoryDto.getId())
                        .orElseThrow(() -> new RuntimeException("Not found: " + categoryDto.getId()));
                categories.add(category);
            });
            existing.setCategories(categories);
        }

        Product updated = productRepository.save(existing);
        return productMapper.toDto(updated);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Not found: " + id);
        }
        productRepository.deleteById(id);
    }
}