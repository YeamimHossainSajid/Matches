package com.example.Matches.features.profile.controller;

import com.example.Matches.features.profile.entity.Profile;
import com.example.Matches.features.profile.payload.request.ProfileRequestDto;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.features.profile.service.ProfileService;
import com.example.Matches.generic.controller.AbstractController;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController extends AbstractController<Profile, ProfileRequestDto, GenericSearchDto> {

    @Autowired
    private ProfileService profileService;

    public ProfileController(IService<Profile, ProfileRequestDto, GenericSearchDto> service) {
        super(service);
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createProfile(@ModelAttribute ProfileRequestDto profileRequestDto) throws IOException {

        profileService.createV2(profileRequestDto,profileRequestDto.getImage());

        return new ResponseEntity<>("Profile created", HttpStatus.CREATED);

    }

    @GetMapping("/matches/{userId}")
    public ResponseEntity<List<ProfileResponseDto>> getMatchingProfiles(@PathVariable Long userId) {
        List<ProfileResponseDto> matchingProfiles = profileService.getMatchingProfiles(userId);
        return ResponseEntity.ok(matchingProfiles);
    }

    @GetMapping("/match/orderBy/{userId}")
    public ResponseEntity<List<Profile>> findMatchingProfiles(@PathVariable Long userId) {
        List<Profile> profiles = profileService.findMatchingProfilesOrderBy(userId);
        return ResponseEntity.ok(profiles);
    }
}
