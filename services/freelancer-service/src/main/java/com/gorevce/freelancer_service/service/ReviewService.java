package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.ReviewRequest;
import com.gorevce.freelancer_service.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    public ReviewResponse createReview(ReviewRequest reviewDto);

    public ReviewResponse getReview(String reviewId);

    public ReviewResponse updateReview(String reviewId, ReviewRequest reviewDto);

    public void deleteReview(String reviewId);

    public List<ReviewResponse> getReviews();

    public List<ReviewResponse> getReviewsByFreelancer(String freelancerId);

}
