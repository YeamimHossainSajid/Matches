package com.example.Matches.features.profile.service.impl;

import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.config.image.service.CloudneryImageService;
import com.example.Matches.features.profile.entity.Profile;
import com.example.Matches.features.profile.payload.request.ProfileRequestDto;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.features.profile.payload.response.ProfileUserResponseDto;
import com.example.Matches.features.profile.repository.ProfileRepository;
import com.example.Matches.features.profile.service.ProfileService;
import com.example.Matches.features.proposeswap.payload.response.SwapUserResponseDto;
import com.example.Matches.features.review.repository.ReviewRepository;
import com.example.Matches.features.review.service.ReviewService;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.repository.AbstractRepository;
import com.example.Matches.generic.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl extends AbstractService<Profile, ProfileRequestDto, GenericSearchDto> implements ProfileService {

    @Autowired
    CloudneryImageService cloudneryImageService;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepo userRepo;

    @Autowired
    ReviewRepository reviewRepository;

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
        profileResponseDto.setSkillsYouWant(profile.getSkillsYouWant());
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
        User user = userRepo.findById(profileRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + profileRequestDto.getUserId()));
        profile.setFullName(profileRequestDto.getFullName());
        profile.setBio(profileRequestDto.getBio());
        profile.setLocation(profileRequestDto.getLocation());
        profile.setPhoneNumber(profileRequestDto.getPhoneNumber());
        profile.setWebsiteUrl(profileRequestDto.getWebsiteUrl());
        profile.setSkills(convertSkillsToList(profileRequestDto.getSkills()));
        profile.setImageUrl(heroImageUrl);
        profile.setSkillsYouWant(convertSkillsToList(profileRequestDto.getSkillsYouWant()));
        profile.setUser(user);

        profileRepository.save(profile);

    }

    public List<ProfileResponseDto> getMatchingProfiles(Long userId) {
        List<Profile> profiles = profileRepository.findMatchingProfilesByUserId(userId);

        List<ProfileResponseDto> responseDtos = new ArrayList<>();

        for (Profile profile : profiles) {
            ProfileResponseDto dto = new ProfileResponseDto();
            dto.setId(profile.getId());
            dto.setFullName(profile.getFullName());
            dto.setBio(profile.getBio());
            dto.setLocation(profile.getLocation());
            dto.setPhoneNumber(profile.getPhoneNumber());
            dto.setSkills(profile.getSkills());
            dto.setWebsiteUrl(profile.getWebsiteUrl());
            dto.setImage(profile.getImageUrl());
            dto.setSkillsYouWant(profile.getSkillsYouWant());
            if (profile.getUser() != null) {
                ProfileUserResponseDto profileDto = new ProfileUserResponseDto();
                profileDto.setId(profile.getUser().getId());
                profileDto.setUsername(profile.getUser().getUsername());
                profileDto.setEmail(profile.getUser().getEmail());
                profileDto.setRating(reviewRepository.findAverageRatingForUser(profile.getUser().getId()));
                profileDto.setIsActive(profile.getUser().getIsAvailable());
                dto.setUser(profileDto);
            }

            responseDtos.add(dto);
        }

        return responseDtos;
    }

    private List<String> convertSkillsToList(String skills) {
        if (skills == null || skills.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(skills.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public List<ProfileResponseDto> findMatchingProfilesOrderBy(Long userId) {

        List<Profile> profiles = profileRepository.findMatchingProfilesByUserIdOrderByAverageRating(userId);

        List<ProfileResponseDto> responseDtos = new ArrayList<>();

        for (Profile profile : profiles) {
            ProfileResponseDto dto = new ProfileResponseDto();
            dto.setId(profile.getId());
            dto.setFullName(profile.getFullName());
            dto.setBio(profile.getBio());
            dto.setLocation(profile.getLocation());
            dto.setPhoneNumber(profile.getPhoneNumber());
            dto.setSkills(profile.getSkills());
            dto.setWebsiteUrl(profile.getWebsiteUrl());
            dto.setImage(profile.getImageUrl());
            dto.setSkillsYouWant(profile.getSkillsYouWant());
            if (profile.getUser() != null) {
                ProfileUserResponseDto profileDto = new ProfileUserResponseDto();
                profileDto.setId(profile.getUser().getId());
                profileDto.setUsername(profile.getUser().getUsername());
                profileDto.setEmail(profile.getUser().getEmail());
                profileDto.setRating(reviewRepository.findAverageRatingForUser(profile.getUser().getId()));
                profileDto.setIsActive(profile.getUser().getIsAvailable());
                dto.setUser(profileDto);
            }

            responseDtos.add(dto);
        }

        return responseDtos;
    }


    public List<ProfileResponseDto> getAllProfilesExceptUserOrderByAverageRating(Long userId) {


        List<Profile> profiles = profileRepository.findAllProfilesExceptUserOrderByAverageRating(userId);

        List<ProfileResponseDto> responseDtos = new ArrayList<>();

        for (Profile profile : profiles) {
            ProfileResponseDto dto = new ProfileResponseDto();
            dto.setId(profile.getId());
            dto.setFullName(profile.getFullName());
            dto.setBio(profile.getBio());
            dto.setLocation(profile.getLocation());
            dto.setPhoneNumber(profile.getPhoneNumber());
            dto.setSkills(profile.getSkills());
            dto.setWebsiteUrl(profile.getWebsiteUrl());
            dto.setImage(profile.getImageUrl());
            dto.setSkillsYouWant(profile.getSkillsYouWant());
            if (profile.getUser() != null) {
                ProfileUserResponseDto profileDto = new ProfileUserResponseDto();
                profileDto.setId(profile.getUser().getId());
                profileDto.setUsername(profile.getUser().getUsername());
                profileDto.setEmail(profile.getUser().getEmail());
                profileDto.setRating(reviewRepository.findAverageRatingForUser(profile.getUser().getId()));
                profileDto.setIsActive(profile.getUser().getIsAvailable());
                dto.setUser(profileDto);
            }

            responseDtos.add(dto);
        }

        return responseDtos;
    }

}
