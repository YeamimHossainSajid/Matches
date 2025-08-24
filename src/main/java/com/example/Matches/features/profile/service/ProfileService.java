package com.example.Matches.features.profile.service;

import com.example.Matches.features.profile.entity.Profile;
import com.example.Matches.features.profile.payload.request.ProfileRequestDto;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService extends IService<Profile, ProfileRequestDto,GenericSearchDto> {

    public void createV2(ProfileRequestDto profileRequestDto, MultipartFile image) throws IOException;

    public List<ProfileResponseDto> getMatchingProfiles(Long userId);

}
