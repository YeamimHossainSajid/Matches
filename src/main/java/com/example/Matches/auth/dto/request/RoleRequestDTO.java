package com.example.Matches.auth.dto.request;
import java.io.Serializable;

public record RoleRequestDTO(

        String roleType

)
        implements Serializable {
}
