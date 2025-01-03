package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.ReviewRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.ReviewService;
import com.gorevce.freelancer_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // create review
    @PostMapping("/create-review")
    @Operation(summary = "Create review", description = "Create review")
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest reviewRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Review created successfully")
                            .httpStatusCode(200)
                            .response(reviewService.createReview(reviewRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }

    // get review by id
    @GetMapping("/get-review")
    @Operation(summary = "Get review by id", description = "Get review by id")
    public ResponseEntity<?> getReviewById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Review retrieved successfully")
                            .httpStatusCode(200)
                            .response(reviewService.getReview(id))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }

    // update review
    @PutMapping("/update-review")
    @Operation(summary = "Update review", description = "Update review")
    public ResponseEntity<?> updateReview(@RequestBody ReviewRequest reviewRequest, @RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Review updated successfully")
                            .httpStatusCode(200)
                            .response(reviewService.updateReview(id, reviewRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }

    // delete review
    @DeleteMapping("/delete-review")
    @Operation(summary = "Delete review", description = "Delete review")
    public ResponseEntity<?> deleteReview(@RequestParam String id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Review deleted successfully")
                            .httpStatusCode(200)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }

    // get all reviews
    @GetMapping("/get-all-reviews")
    @Operation(summary = "Get all reviews", description = "Get all reviews")
    public ResponseEntity<?> getReviews() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Reviews retrieved successfully")
                            .httpStatusCode(200)
                            .response(reviewService.getReviews())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }

    // get reviews by freelancer
    @GetMapping("/get-reviews")
    @Operation(summary = "Get reviews by freelancer", description = "Get reviews by freelancer")
    public ResponseEntity<?> getReviewsByFreelancer(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Reviews retrieved successfully")
                            .httpStatusCode(200)
                            .response(reviewService.getReviewsByFreelancer(freelancerId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }

    // get reviews by reviewer
    // TODO: implement getReviewsByReviewer
}
