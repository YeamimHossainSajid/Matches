package com.example.Matches.features.dispute.service;

import com.example.Matches.features.dispute.dto.request.DisputeRequestDto;
import com.example.Matches.features.dispute.dto.response.DisputeResponseDto;
import com.example.Matches.features.dispute.entity.Dispute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DisputeService {

    String createDispute(DisputeRequestDto disputeRequestDto, MultipartFile file) throws IOException;

    DisputeResponseDto getDisputeById(Long id);

    List<DisputeResponseDto> getAllDisputes();

    String updateDispute(Long id, Dispute dispute);

    void deleteDispute(Long id);

}
