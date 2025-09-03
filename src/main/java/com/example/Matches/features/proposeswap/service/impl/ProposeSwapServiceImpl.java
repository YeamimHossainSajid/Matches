package com.example.Matches.features.proposeswap.service.impl;

import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.features.proposeswap.payload.request.ProposeSwapRequestDto;
import com.example.Matches.features.proposeswap.payload.response.ProposeSwapResponseDto;
import com.example.Matches.features.proposeswap.repository.ProposeSwapRepository;
import com.example.Matches.features.proposeswap.service.ProposeSwapService;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.example.Matches.generic.repository.AbstractRepository;
import com.example.Matches.generic.service.AbstractService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProposeSwapServiceImpl implements ProposeSwapService {

    private final ProposeSwapRepository proposeSwapRepository;
    private final UserRepo userRepository;
    public ProposeSwapServiceImpl(ProposeSwapRepository proposeSwapRepository,
                                  UserRepo userRepository) {
        this.proposeSwapRepository = proposeSwapRepository;
        this.userRepository = userRepository;
    }

    private ProposeSwapResponseDto convertToResponseDto(ProposeSwap swap) {
        ProposeSwapResponseDto dto = new ProposeSwapResponseDto();
        dto.setId(swap.getId());
        dto.setYourOffer(swap.getYourOffer());
        dto.setWantInReturn(swap.getWantInReturn());
        dto.setSwapDetails(swap.getSwapDetails());
        dto.setSwapDuration(swap.getSwapDuration());
        dto.setAssociatedDeposit(swap.getAssociatedDeposit());
        dto.setStatus(swap.getStatus());
        dto.setSenderId(swap.getSender() != null ? swap.getSender().getId() : null);
        return dto;
    }



    public ProposeSwap sendSwap(Long senderId, Long receiverId,
                                String yourOffer, String wantInReturn,
                                String swapDetails, String swapDuration,
                                String associatedDeposit) {

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        ProposeSwap swap = new ProposeSwap();
        swap.setSender(sender);
        swap.setReceiver(receiver);
        swap.setYourOffer(yourOffer);
        swap.setWantInReturn(wantInReturn);
        swap.setSwapDetails(swapDetails);
        swap.setSwapDuration(swapDuration);
        swap.setAssociatedDeposit(associatedDeposit);
        swap.setStatus(RequestStatus.PENDING);

        return proposeSwapRepository.save(swap);
    }

    public ProposeSwap respondToSwap(Long swapId, RequestStatus status) {
        ProposeSwap swap = proposeSwapRepository.findById(swapId)
                .orElseThrow(() -> new RuntimeException("Swap not found"));

        swap.setStatus(status);

        return proposeSwapRepository.save(swap);
    }

    public List<ProposeSwapResponseDto> getPendingSwaps(Long receiverId) {
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        List<ProposeSwap> swaps = proposeSwapRepository.findByReceiverAndStatus(receiver, RequestStatus.PENDING);

        return swaps.stream()
                .map(this::convertToResponseDto)
                .toList();
    }

}
