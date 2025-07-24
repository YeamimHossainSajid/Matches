package com.example.Matches.auth.service;

import com.example.Matches.auth.dto.request.UserRequestDTO;
import com.example.Matches.auth.dto.request.UserRoleRequestDTO;
import com.example.Matches.auth.dto.request.UserUpdateRequestDto;
import com.example.Matches.auth.dto.response.CustomUserResponseDTO;
import com.example.Matches.auth.dto.response.UserResponseDto;
import com.example.Matches.auth.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    public void create(UserRequestDTO requestDto) throws IOException;
    public CustomUserResponseDTO readOne(Long id );
    public User setUserRoles(UserRoleRequestDTO requestDTO );
    public void updateUser(Long id, UserUpdateRequestDto userRequestDTO) throws IOException;
    public UserResponseDto searchByUsername(String username);

}