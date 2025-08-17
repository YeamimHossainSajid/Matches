package com.example.Matches.auth.dto.response;

import java.util.List;
import java.util.Set;

public interface CustomUserResponseDTO {
    Long getId();
    String getUsername();
    String getEmail();
    ProfileInfo getProfile();
    Set<RoleInfo> getRoles();

    interface RoleInfo {
        Long getId();
        String getRoleType();
    }

    interface ProfileInfo {
        Long getId();
        String getFullName();
        String getPhoneNumber();
        String getLocation();
        String getBio();
        String getImageUrl();
        String getWebsiteUrl();
        List<String> getSkills();
        List<String> getSkillsYouWant();
    }
}
