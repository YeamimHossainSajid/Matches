package com.example.Matches.auth.service;


import com.example.Matches.auth.dto.request.LoginRequestDTO;
import com.example.Matches.auth.dto.response.LoginResponseDTO;

public interface IAuthenticationService {
    LoginResponseDTO login(LoginRequestDTO requestDTO );
}
