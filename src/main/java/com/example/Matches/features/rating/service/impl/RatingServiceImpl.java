package com.example.Matches.features.rating.service.impl;

import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.features.rating.entity.Rating;
import com.example.Matches.features.rating.payload.request.RatingRequestDto;
import com.example.Matches.features.rating.repository.RatingRepository;
import com.example.Matches.features.rating.service.RatingService;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.example.Matches.generic.repository.AbstractRepository;
import com.example.Matches.generic.service.AbstractService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class RatingServiceImpl extends AbstractService<Rating, RatingRequestDto, GenericSearchDto> implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepo userRepository;

    public RatingServiceImpl(AbstractRepository<Rating> repository, UserRepo userRepository, RatingRepository ratingRepository) {
        super(repository);
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

    @Override
    protected <T extends BaseResponseDto> T convertToResponseDto(Rating rating) {
        return null;
    }

    @Override
    protected Rating convertToEntity(RatingRequestDto ratingRequestDto) throws IOException {
        return null;
    }

    @Override
    protected Rating updateEntity(RatingRequestDto ratingRequestDto, Rating entity) throws IOException {
        return null;
    }

    @Override
    protected Specification<Rating> buildSpecification(GenericSearchDto searchDto) {
        return null;
    }
}
