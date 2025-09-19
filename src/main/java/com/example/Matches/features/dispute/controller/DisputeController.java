package com.example.Matches.features.dispute.controller;

import com.example.Matches.features.dispute.dto.request.DisputeRequestDto;
import com.example.Matches.features.dispute.dto.response.DisputeResponseDto;
import com.example.Matches.features.dispute.service.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/disputes")
public class DisputeController {

    private final DisputeService disputeService;

    @Autowired
    public DisputeController(DisputeService disputeService) {
        this.disputeService = disputeService;
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createDispute(@ModelAttribute DisputeRequestDto disputeRequestDto) throws IOException {
        String result = disputeService.createDispute(disputeRequestDto, disputeRequestDto.getFile());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<DisputeResponseDto>> getAllDisputes() {
        List<DisputeResponseDto> disputes = disputeService.getAllDisputes();
        return ResponseEntity.ok(disputes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisputeResponseDto> getDisputeById(@PathVariable Long id) {
        DisputeResponseDto dispute = disputeService.getDisputeById(id);
        return ResponseEntity.ok(dispute);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateDispute(
//            @PathVariable Long id,
//            @RequestBody Dispute updatedDispute) {
//        String result = disputeService.updateDispute(id, updatedDispute);
//        return ResponseEntity.ok(result);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDispute(@PathVariable Long id) {
        disputeService.deleteDispute(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}

