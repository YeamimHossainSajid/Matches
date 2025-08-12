package com.example.Matches.features.profile.payload.request;

import com.example.Matches.generic.payload.request.IDto;
import com.example.Matches.generic.payload.request.SDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDto implements IDto {

    private String fullName;

    private String phoneNumber;

    private String location;

    private String bio;

    private MultipartFile image;

    private String websiteUrl;

    private List<String> skills;

}
