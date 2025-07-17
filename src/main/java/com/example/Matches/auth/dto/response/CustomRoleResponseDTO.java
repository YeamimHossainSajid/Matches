package com.example.Matches.auth.dto.response;

import java.util.Set;

public interface CustomRoleResponseDTO {

    Long getId();

    String getRoleType();

    Set< UserInfo > getUsers();

    interface UserInfo {
        Long getId();

        String getUsername();

        String getEmail();
    }
}
