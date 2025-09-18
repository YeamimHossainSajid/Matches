package com.example.Matches.features.profile.payload.response;

import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto extends BaseResponseDto {

    private Long id;

    private String fullName;

    private String phoneNumber;

    private String location;

    private String bio;

    private String image;

    private String websiteUrl;

    private List<String> skills;

    private List<String> skillsYouWant;

    ProfileUserResponseDto user;

}
