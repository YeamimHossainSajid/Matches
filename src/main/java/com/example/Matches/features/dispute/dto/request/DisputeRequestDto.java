package com.example.Matches.features.dispute.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DisputeRequestDto {

    private String yourStatement;

    private MultipartFile file;

    private Long swapId;

    private String swapTitle;

    private Long sender;

    private Long receiver;
}
