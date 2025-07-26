package com.example.Matches.features.payload.response;

import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto extends BaseResponseDto {

    private String fullName;

    private String phoneNumber;

    private String location;

    private String bio;

}
