package com.example.Matches.features.service;

import com.example.Matches.features.entity.profile.Profile;
import com.example.Matches.features.payload.request.ProfileRequestDto;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;

public interface ProfileService extends IService<Profile, ProfileRequestDto,GenericSearchDto> {

}
