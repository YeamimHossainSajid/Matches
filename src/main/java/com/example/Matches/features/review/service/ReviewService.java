package com.example.Matches.features.review.service;

import com.example.Matches.features.review.entity.Review;
import com.example.Matches.features.review.payload.response.UserProfileWithReviewsDto;

public interface ReviewService {

    public Review addReview(Long reviewerId, Long reviewedUserId, String reviewText);
    public UserProfileWithReviewsDto getUserWithProfileAndReviews(Long userId);

}
