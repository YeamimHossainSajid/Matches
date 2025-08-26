package com.example.Matches.features.rating.service;

import com.example.Matches.features.rating.entity.Rating;
import com.example.Matches.features.rating.payload.request.RatingRequestDto;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;

public interface RatingService extends IService<Rating, RatingRequestDto, GenericSearchDto> {
    public Rating giveOrUpdateRating(Long raterId, Long rateeId, int ratingValue);
    public void removeRating(Long raterId, Long rateeId);
    public Double getAverageRating(Long userId);
}
