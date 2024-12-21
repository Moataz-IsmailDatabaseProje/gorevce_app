package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.request.ReviewRequest;
import com.gorevce.freelancer_service.dto.response.ReviewResponse;
import com.gorevce.freelancer_service.event.added.ReviewAddedEvent;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.Review;
import com.gorevce.freelancer_service.repository.ReviewRepository;
import com.gorevce.freelancer_service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public ReviewResponse createReview(ReviewRequest reviewDto) {

        // check freelancer id is not null
        if(reviewDto.getFreelancerId() == null){
            throw new CustomException(
                    "Freelancer id is required",
                    400,
                    Map.of(
                            "freelancerId","null"
                    )
            );
        }

        // create review
        Review review = Review.builder()
                .reviewerId(reviewDto.getReviewerId())
                .freelancerId(reviewDto.getFreelancerId())
                .rating(reviewDto.getRating())
                .review(reviewDto.getReview())
                .imageUrl(reviewDto.getImageUrl())
                .createdAt(new Date())
                .build();
        // save review
        Review savedReview = reviewRepository.save(review); // save review
        eventPublisher.publishEvent(new ReviewAddedEvent(this, savedReview.getFreelancerId(), savedReview.getId()));
        // return review
        return ReviewResponse.builder()
                .id(savedReview.getId())
                .reviewerId(savedReview.getReviewerId())
                .freelancerId(savedReview.getFreelancerId())
                .rating(savedReview.getRating())
                .review(savedReview.getReview())
                .imageUrl(savedReview.getImageUrl())
                .createdAt(savedReview.getCreatedAt())
                .build();

    }

    @Override
    public ReviewResponse getReview(String reviewId) {
        // get review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new CustomException(
                                "Review not found",
                                404,
                                Map.of(
                                        "reviewId",reviewId
                                )
                        )

                );
        // return review
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewerId(review.getReviewerId())
                .freelancerId(review.getFreelancerId())
                .rating(review.getRating())
                .review(review.getReview())
                .imageUrl(review.getImageUrl())
                .createdAt(review.getCreatedAt())
                .build();
    }

    @Override
    public ReviewResponse updateReview(String reviewId, ReviewRequest reviewDto) {
        // get review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new CustomException(
                                "Review not found",
                                404,
                                Map.of(
                                        "reviewId",reviewId
                                )
                        )
                );
        // update review
        review.setReviewerId(reviewDto.getReviewerId());
        review.setFreelancerId(reviewDto.getFreelancerId());
        review.setRating(reviewDto.getRating());
        review.setReview(reviewDto.getReview());
        review.setImageUrl(reviewDto.getImageUrl());
        // save review
        Review savedReview = reviewRepository.save(review);
        // return review
        return ReviewResponse.builder()
                .id(savedReview.getId())
                .reviewerId(savedReview.getReviewerId())
                .freelancerId(savedReview.getFreelancerId())
                .rating(savedReview.getRating())
                .review(savedReview.getReview())
                .imageUrl(savedReview.getImageUrl())
                .createdAt(savedReview.getCreatedAt())
                .build();
    }

    @Override
    public void deleteReview(String reviewId) {
        // get review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(
                        () -> new CustomException(
                                "Review not found",
                                404,
                                Map.of(
                                        "reviewId", reviewId
                                )
                        )
                );
        // delete review
        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewResponse> getReviews() {
        // get reviews
        List<Review> reviews = reviewRepository.findAll();
        // return reviews
        return reviews.stream()
                .map(review -> ReviewResponse.builder()
                        .id(review.getId())
                        .reviewerId(review.getReviewerId())
                        .freelancerId(review.getFreelancerId())
                        .rating(review.getRating())
                        .review(review.getReview())
                        .imageUrl(review.getImageUrl())
                        .createdAt(review.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public List<ReviewResponse> getReviewsByFreelancer(String freelancerId) {
        // get reviews by freelancer
        List<Review> reviews = reviewRepository.findByFreelancerId(freelancerId);
        // return reviews
        return reviews.stream()
                .map(review -> ReviewResponse.builder()
                        .id(review.getId())
                        .reviewerId(review.getReviewerId())
                        .freelancerId(review.getFreelancerId())
                        .rating(review.getRating())
                        .review(review.getReview())
                        .imageUrl(review.getImageUrl())
                        .createdAt(review.getCreatedAt())
                        .build())
                .toList();
    }
}
