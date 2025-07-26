package com.example.Matches.features.service.impl;

import com.example.Matches.features.entity.profile.Profile;
import com.example.Matches.features.payload.request.ProfileRequestDto;
import com.example.Matches.features.payload.response.ProfileResponseDto;
import com.example.Matches.features.service.ProfileService;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.example.Matches.generic.repository.AbstractRepository;
import com.example.Matches.generic.service.AbstractService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProfileServiceImpl extends AbstractService<Profile, ProfileRequestDto, GenericSearchDto> implements ProfileService {


    public ProfileServiceImpl(AbstractRepository<Profile> repository) {
        super(repository);
    }

    @Override
    protected ProfileResponseDto convertToResponseDto(Profile profile) {
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.setFullName(profile.getFullName());
        profileResponseDto.setBio(profile.getBio());
        profileResponseDto.setLocation(profile.getLocation());
        profileResponseDto.setPhoneNumber(profile.getPhoneNumber());
        return profileResponseDto ;
    }

    @Override
    protected Profile convertToEntity(ProfileRequestDto profileRequestDto) throws IOException {
        Profile profile = new Profile();
        profile.setFullName(profileRequestDto.getFullName());
        profile.setBio(profileRequestDto.getBio());
        profile.setLocation(profileRequestDto.getLocation());
        profile.setPhoneNumber(profileRequestDto.getPhoneNumber());
        return profile;
    }

    @Override
    protected Profile updateEntity(ProfileRequestDto profileRequestDto, Profile entity) throws IOException {
        return null;
    }

    @Override
    protected Specification<Profile> buildSpecification(GenericSearchDto searchDto) {
        return null;
    }
}
