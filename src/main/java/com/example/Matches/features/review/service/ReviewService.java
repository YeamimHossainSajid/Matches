package com.example.Matches.features.review.service;

import com.example.Matches.features.review.entity.Review;
import com.example.Matches.features.review.payload.request.ReviewRequestDto;
import com.example.Matches.features.review.payload.response.UserProfileWithReviewsDto;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.AbstractService;
import com.example.Matches.generic.service.IService;

public interface ReviewService extends IService<Review, ReviewRequestDto, GenericSearchDto> {

    public Review addReview(Long reviewerId, Long reviewedUserId, String reviewText);
    public UserProfileWithReviewsDto getUserWithProfileAndReviews(Long userId);

}
