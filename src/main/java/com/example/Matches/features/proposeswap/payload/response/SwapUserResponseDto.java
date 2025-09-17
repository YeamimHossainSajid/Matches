package com.example.Matches.features.proposeswap.payload.response;

import com.example.Matches.auth.dto.response.CustomRoleResponseDTO;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.features.review.payload.response.ReviewResponseDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SwapUserResponseDto {
        private Long id;
        private String username;
        private String email;
        private Double rating;
        private Boolean isActive;

}
