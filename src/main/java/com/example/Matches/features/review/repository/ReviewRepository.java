package com.example.Matches.features.review.repository;

import com.example.Matches.features.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.reviewedUser.id = :rateeId")
    Double findAverageRatingForUser(@Param("rateeId") Long rateeId);
}
