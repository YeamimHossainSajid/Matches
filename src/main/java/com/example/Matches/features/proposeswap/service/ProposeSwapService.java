package com.example.Matches.features.proposeswap.service;

import com.example.Matches.auth.model.User;
import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.features.proposeswap.payload.request.ProposeSwapRequestDto;
import com.example.Matches.features.proposeswap.payload.response.ProposeSwapResponseDto;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.AbstractService;
import com.example.Matches.generic.service.IService;

import java.util.List;
import java.util.Map;

public interface ProposeSwapService  {
    public ProposeSwap sendSwap(Long senderId, Long receiverId,
                                String yourOffer, String wantInReturn,
                                String swapDetails, String swapDuration,
                                String associatedDeposit);
    public ProposeSwap respondToSwap(Long swapId, RequestStatus status);

    public List<ProposeSwapResponseDto> getPendingSwaps(Long receiverId);

//    List<ProposeSwap> findByUserAndStatus(
//            List<RequestStatus> statuses,
//            Long userId
//    );
    Map<String, Long> countSwapsByUser(Long userId);

    public Map<RequestStatus, List<ProposeSwapResponseDto>> getSwapsGroupedByStatus(Long  userId, List<RequestStatus> statuses);

}
