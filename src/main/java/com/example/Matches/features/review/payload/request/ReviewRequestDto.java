package com.example.Matches.features.review.payload.request;

import com.example.Matches.generic.payload.request.IDto;

public class ReviewRequestDto implements IDto {
    private Long reviewerId;
    private Long reviewedUserId;
    private String reviewText;
}
