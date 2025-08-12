package com.example.Matches.features.profile.service.impl;

import com.example.Matches.config.image.service.CloudneryImageService;
import com.example.Matches.features.profile.entity.profile.Profile;
import com.example.Matches.features.profile.payload.request.ProfileRequestDto;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.features.profile.repository.profilerepo.ProfileRepository;
import com.example.Matches.features.profile.service.ProfileService;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.repository.AbstractRepository;
import com.example.Matches.generic.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ProfileServiceImpl extends AbstractService<Profile, ProfileRequestDto, GenericSearchDto> implements ProfileService {

    @Autowired
    CloudneryImageService cloudneryImageService;
    @Autowired
    ProfileRepository profileRepository;

    public ProfileServiceImpl(AbstractRepository<Profile> repository) {
        super(repository);
    }

    @Override
    protected ProfileResponseDto convertToResponseDto(Profile profile) {
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.setId(profile.getId());
        profileResponseDto.setFullName(profile.getFullName());
        profileResponseDto.setBio(profile.getBio());
        profileResponseDto.setLocation(profile.getLocation());
        profileResponseDto.setPhoneNumber(profile.getPhoneNumber());
        profileResponseDto.setSkills(profile.getSkills());
        profileResponseDto.setWebsiteUrl(profile.getWebsiteUrl());
        profileResponseDto.setImage(profile.getImageUrl());
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

    public void createV2(ProfileRequestDto profileRequestDto, MultipartFile image) throws IOException {
        Map<String, Object> heroUploadResult = cloudneryImageService.upload(image);
        String heroImageUrl = (String) heroUploadResult.get("secure_url");

        Profile profile = new Profile();

        profile.setFullName(profileRequestDto.getFullName());
        profile.setBio(profileRequestDto.getBio());
        profile.setLocation(profileRequestDto.getLocation());
        profile.setPhoneNumber(profileRequestDto.getPhoneNumber());
        profile.setWebsiteUrl(profileRequestDto.getWebsiteUrl());
        profile.setSkills(profileRequestDto.getSkills());
        profile.setImageUrl(heroImageUrl);

        profileRepository.save(profile);

    }
}
