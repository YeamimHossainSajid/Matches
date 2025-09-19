package com.example.Matches.features.dispute.service.impl;

import com.example.Matches.auth.service.UserService;
import com.example.Matches.config.image.service.CloudneryImageService;
import com.example.Matches.features.dispute.dto.request.DisputeRequestDto;
import com.example.Matches.features.dispute.dto.response.DisputeResponseDto;
import com.example.Matches.features.dispute.entity.Dispute;
import com.example.Matches.features.dispute.repository.DisputeRepository;
import com.example.Matches.features.dispute.service.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DisputeServiceImpl implements DisputeService {


    private final DisputeRepository disputeRepository;
    private final CloudneryImageService cloudneryImageService;

    @Autowired
    public DisputeServiceImpl(DisputeRepository disputeRepository,
                              CloudneryImageService cloudneryImageService) {
        this.disputeRepository = disputeRepository;
        this.cloudneryImageService = cloudneryImageService;
    }

    private Dispute convertToEntity(DisputeRequestDto dto, MultipartFile file) throws IOException {
        Dispute dispute = new Dispute();
        Map<String, Object> heroUploadResult = cloudneryImageService.upload(file);
        String fileUrl = (String) heroUploadResult.get("secure_url");
        dispute.setYourStatement(dto.getYourStatement());
        dispute.setFile(fileUrl);
        dispute.setReceiver(dto.getReceiver());
        dispute.setSender(dto.getSender());
        dispute.setSwapId(dto.getSwapId());
        dispute.setSwapTitle(dto.getSwapTitle());
        return dispute;
    }

    private DisputeResponseDto convertToResponseDto(Dispute dispute) {

        DisputeResponseDto dto = new DisputeResponseDto();

        dto.setId(dispute.getId());
        dto.setYourStatement(dispute.getYourStatement());
        dto.setFile(dispute.getFile());
        dto.setReceiver(dispute.getReceiver());
        dto.setSender(dispute.getSender());
        dto.setSwapId(dispute.getSwapId());
        dto.setSwapTitle(dispute.getSwapTitle());

        return dto;
    }

    @Override
    public String createDispute(DisputeRequestDto disputeRequestDto, MultipartFile file) throws IOException {
        Dispute dispute=convertToEntity(disputeRequestDto,file);
         disputeRepository.save(dispute);
         return "success";
    }

    @Override
    public DisputeResponseDto getDisputeById(Long id) {
        Dispute dispute=disputeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispute not found with id " + id));
        return convertToResponseDto(dispute);
    }

    @Override
    public List<DisputeResponseDto> getAllDisputes() {
        List<Dispute> disputes = disputeRepository.findAll();
        return disputes.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public String updateDispute(Long id, Dispute updatedDispute) {
        Dispute existing = disputeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispute not found with id " + id));

        existing.setYourStatement(updatedDispute.getYourStatement());
        existing.setFile(updatedDispute.getFile());
        existing.setReceiver(updatedDispute.getReceiver());
        existing.setSender(updatedDispute.getSender());
        existing.setSwapId(updatedDispute.getSwapId());
        existing.setSwapTitle(updatedDispute.getSwapTitle());

         disputeRepository.save(existing);
         return "success";
    }

    @Override
    public void deleteDispute(Long id) {
        if (!disputeRepository.existsById(id)) {
            throw new RuntimeException("Dispute not found with id " + id);
        }
        disputeRepository.deleteById(id);
    }
}
