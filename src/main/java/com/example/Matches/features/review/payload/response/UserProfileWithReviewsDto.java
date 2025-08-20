package com.example.Matches.features.review.payload.response;

import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileWithReviewsDto {
    private Long userId;
    private String username;
    private String email;

    private ProfileResponseDto profile;
    private List<ReviewResponseDto> reviewsReceived;
}

