package com.example.Matches.auth.service;


import com.example.Matches.auth.dto.response.CustomRoleResponseDTO;

public interface RoleService {

    public CustomRoleResponseDTO readOne(Long id );
    public String delete( Long id );

}
