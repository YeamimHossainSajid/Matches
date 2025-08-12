package com.example.Matches.features.profile.service;

import com.example.Matches.features.profile.entity.profile.Profile;
import com.example.Matches.features.profile.payload.request.ProfileRequestDto;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService extends IService<Profile, ProfileRequestDto,GenericSearchDto> {

    public void createV2(ProfileRequestDto profileRequestDto, MultipartFile image) throws IOException;

}
