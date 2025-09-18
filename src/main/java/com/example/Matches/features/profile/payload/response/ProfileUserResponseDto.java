package com.example.Matches.features.profile.payload.response;

import lombok.Data;

@Data
public class ProfileUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private Double rating;
    private Boolean isActive;

}
