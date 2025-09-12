package com.example.Matches.features.review.service;

import com.example.Matches.features.review.entity.Review;
import com.example.Matches.features.review.payload.request.ReviewRequestDto;
import com.example.Matches.features.review.payload.response.UserProfileWithReviewsDto;

public interface ReviewService {

    public Review addReview(ReviewRequestDto reviewRequestDto);
    public UserProfileWithReviewsDto getUserWithProfileAndReviews(Long userId);

}
