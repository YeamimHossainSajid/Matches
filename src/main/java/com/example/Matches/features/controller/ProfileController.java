package com.example.Matches.features.controller;

import com.example.Matches.features.entity.profile.Profile;
import com.example.Matches.features.payload.request.ProfileRequestDto;
import com.example.Matches.generic.controller.AbstractController;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController extends AbstractController<Profile, ProfileRequestDto, GenericSearchDto> {
    public ProfileController(IService<Profile, ProfileRequestDto, GenericSearchDto> service) {
        super(service);
    }
}
