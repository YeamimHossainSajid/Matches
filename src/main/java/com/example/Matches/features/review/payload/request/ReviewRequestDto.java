package com.example.Matches.features.review.payload.request;

import com.example.Matches.generic.payload.request.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewRequestDto implements IDto {
    private Long reviewerId;
    private Long reviewedUserId;
    private String reviewText;
    private int rating;
}
