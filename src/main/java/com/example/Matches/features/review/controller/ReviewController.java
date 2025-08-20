package com.example.Matches.features.review.controller;

import com.example.Matches.features.review.entity.Review;
import com.example.Matches.features.review.payload.request.ReviewRequestDto;
import com.example.Matches.features.review.payload.response.UserProfileWithReviewsDto;
import com.example.Matches.features.review.service.ReviewService;
import com.example.Matches.generic.controller.AbstractController;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController extends AbstractController<Review, ReviewRequestDto, GenericSearchDto> {
     public ReviewService reviewService;
    public ReviewController(IService<Review, ReviewRequestDto, GenericSearchDto> service,ReviewService reviewService) {
        super(service);
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
