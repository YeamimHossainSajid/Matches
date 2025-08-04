package com.example.Matches.auth.service;


import com.example.Matches.auth.dto.request.UserRequestDTO;
import com.example.Matches.auth.dto.request.UserRoleRequestDTO;
import com.example.Matches.auth.dto.response.CustomUserResponseDTO;
import com.example.Matches.auth.model.User;

public interface UserService {

    public String create(UserRequestDTO requestDto);
    public CustomUserResponseDTO readOne(Long id );
    public User setUserRoles(UserRoleRequestDTO requestDTO );
    public void updateUser(Long id, UserRequestDTO userRequestDTO);
    public CustomUserResponseDTO searchByUsername(String username);
    public String validateOtp(String email, String otp);
}