package com.example.Matches.features.payload.request;

import com.example.Matches.generic.payload.request.IDto;
import com.example.Matches.generic.payload.request.SDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDto implements IDto {

    private String fullName;

    private String phoneNumber;

    private String location;

    private String bio;

}
