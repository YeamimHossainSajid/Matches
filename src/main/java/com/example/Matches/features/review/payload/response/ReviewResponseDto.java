package com.example.Matches.features.review.payload.response;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ReviewResponseDto {
    private Long id;
    private String reviewText;
    private Long reviewerId;
    private String reviewerName;
    private String reviewerEmail;
    private String reviewerProfilePic;
}

