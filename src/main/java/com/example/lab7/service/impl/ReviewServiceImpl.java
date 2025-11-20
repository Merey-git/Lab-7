package com.example.lab7.service.impl;

import com.example.lab7.dto.ReviewDto;
import com.example.lab7.entity.Product;
import com.example.lab7.entity.Review;
import com.example.lab7.mapper.ReviewMapper;
import com.example.lab7.repository.ProductRepository;
import com.example.lab7.repository.ReviewRepository;
import com.example.lab7.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Product product = productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Not found: " + reviewDto.getProductId()));

        Review review = reviewMapper.toEntity(reviewDto);

        review.setProduct(product);

        Review saved = reviewRepository.save(review);

        return reviewMapper.toDto(saved);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviewMapper.toDtoList(reviews);
    }

    @Override
    public List<ReviewDto> getReviewsByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Not found: " + productId);
        }

        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviewMapper.toDtoList(reviews);
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
        return reviewMapper.toDto(review);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Not found: " + id);
        }
        reviewRepository.deleteById(id);
    }
}