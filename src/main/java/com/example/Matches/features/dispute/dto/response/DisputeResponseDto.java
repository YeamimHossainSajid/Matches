package com.example.Matches.features.dispute.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DisputeResponseDto {

    private Long id;

    private String yourStatement;

    private String file;

    private Long swapId;

    private String swapTitle;

    private Long sender;

    private Long receiver;
}
