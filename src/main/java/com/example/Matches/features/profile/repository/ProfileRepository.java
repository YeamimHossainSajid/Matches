package com.example.Matches.features.profile.repository;

import com.example.Matches.features.profile.entity.Profile;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.generic.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends AbstractRepository<Profile> {

    @Query("""
    SELECT DISTINCT p
    FROM Profile p
    JOIN p.skills s
    WHERE s IN (
        SELECT sw
        FROM Profile myP
        JOIN myP.skillsYouWant sw
        WHERE myP.user.id = :userId
    )
    AND p.user.id <> :userId
    """)
    List<Profile> findMatchingProfilesByUserId(@Param("userId") Long userId);

    @Query("""
    SELECT p
    FROM Profile p
    JOIN p.skills s
    LEFT JOIN p.user.reviewsReceived rr
    WHERE p.user.id <> :userId
      AND s IN (
        SELECT sw
        FROM Profile myP
        JOIN myP.skillsYouWant sw
        WHERE myP.user.id = :userId
      )
    GROUP BY p
    ORDER BY 
      CASE WHEN AVG(rr.rating) IS NULL THEN 1 ELSE 0 END,
      AVG(rr.rating) DESC
    """)
    List<Profile> findMatchingProfilesByUserIdOrderByAverageRating(@Param("userId") Long userId);


    @Query("""
    SELECT p
    FROM Profile p
    LEFT JOIN p.user.reviewsReceived rr
    WHERE p.user.id <> :userId
    GROUP BY p
    ORDER BY 
      CASE WHEN AVG(rr.rating) IS NULL THEN 1 ELSE 0 END,
      AVG(rr.rating) DESC
    """)
    List<Profile> findAllProfilesExceptUserOrderByAverageRating(@Param("userId") Long userId);




}
