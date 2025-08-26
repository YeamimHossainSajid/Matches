package com.example.Matches.features.rating.repository;

import com.example.Matches.features.rating.entity.Rating;
import com.example.Matches.generic.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepository extends AbstractRepository<Rating> {

    Optional<Rating> findByRaterIdAndRateeId(Long raterId, Long rateeId);
    void deleteByRaterIdAndRateeId(Long raterId, Long rateeId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.ratee.id = :rateeId")
    Double findAverageRatingForUser(@Param("rateeId") Long rateeId);
}
