package com.example.Matches.auth.controller;

import com.example.Matches.auth.dto.request.LoginRequestDTO;
import com.example.Matches.auth.dto.response.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO );

}
