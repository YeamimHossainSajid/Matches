package com.example.Matches.features.review.controller;

import com.example.Matches.features.review.payload.response.UserProfileWithReviewsDto;
import com.example.Matches.features.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController{

    public ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService=reviewService;

    }

    @PostMapping("createReview")
    public ResponseEntity<String>createReview(Long reviewerId, Long reviewedUserId, String reviewText) {
       reviewService.addReview(reviewerId, reviewedUserId, reviewText);
       return ResponseEntity.ok("Review created");
    }

    @GetMapping("/{userId}/details")
    public ResponseEntity<UserProfileWithReviewsDto> getUserWithProfileAndReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getUserWithProfileAndReviews(userId));
    }
}
