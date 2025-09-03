package com.example.Matches.features.rating.service.impl;

import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.features.rating.entity.Rating;

import com.example.Matches.features.rating.repository.RatingRepository;
import com.example.Matches.features.rating.service.RatingService;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepo userRepository;

    public RatingServiceImpl( UserRepo userRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }


    public Rating giveOrUpdateRating(Long raterId, Long rateeId, int ratingValue) {
        if (ratingValue < 1 || ratingValue > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        User rater = userRepository.findById(raterId)
                .orElseThrow(() -> new RuntimeException("Rater not found"));
        User ratee = userRepository.findById(rateeId)
                .orElseThrow(() -> new RuntimeException("Ratee not found"));

        Optional<Rating> existing = ratingRepository.findByRaterIdAndRateeId(raterId, rateeId);

        Rating rating;
        if (existing.isPresent()) {

            rating = existing.get();
            rating.setRating(ratingValue);
        } else {

            rating = new Rating();
            rating.setRater(rater);
            rating.setRatee(ratee);
            rating.setRating(ratingValue);
        }

        return ratingRepository.save(rating);
    }


    public void removeRating(Long raterId, Long rateeId) {
        ratingRepository.deleteByRaterIdAndRateeId(raterId, rateeId);
    }

    public Double getAverageRating(Long userId) {
        return ratingRepository.findAverageRatingForUser(userId);
    }

}
