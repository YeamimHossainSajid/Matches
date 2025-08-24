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
    List<ProfileResponseDto> findMatchingProfilesByUserId(@Param("userId") Long userId);


}
