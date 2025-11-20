package com.example.lab7.mapper;

import com.example.lab7.dto.ReviewDto;
import com.example.lab7.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "product.id", target = "productId")
    ReviewDto toDto(Review review);

    @Mapping(source = "productId", target = "product.id")
    Review toEntity(ReviewDto dto);

    List<ReviewDto> toDtoList(List<Review> reviews);
}