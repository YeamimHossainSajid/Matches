package com.example.Matches.features.proposeswap.payload.response;

import com.example.Matches.auth.model.User;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import lombok.Data;

@Data
public class ProposeSwapResponseDto {

    private Long id;

    private String yourOffer;

    private String wantInReturn;

    private String swapDetails;

    private String swapDuration;

    private String associatedDeposit;

    private RequestStatus status;

    private String swapTitle;

    private SwapUserResponseDto sender;

}
