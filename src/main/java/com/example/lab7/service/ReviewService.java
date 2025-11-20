package com.example.lab7.service;

import com.example.lab7.dto.ReviewDto;
import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto);

    List<ReviewDto> getAllReviews();

    List<ReviewDto> getReviewsByProductId(Long productId);

    ReviewDto getReviewById(Long id);

    void deleteReview(Long id);
}