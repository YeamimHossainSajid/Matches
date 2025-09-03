package com.example.Matches.features.rating.service;

import com.example.Matches.features.rating.entity.Rating;

public interface RatingService  {
    public Rating giveOrUpdateRating(Long raterId, Long rateeId, int ratingValue);
    public void removeRating(Long raterId, Long rateeId);
    public Double getAverageRating(Long userId);
}
