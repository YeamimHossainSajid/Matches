package com.example.Matches.features.proposeswap.entity;

import com.example.Matches.auth.model.User;
import com.example.Matches.generic.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProposeSwap  extends BaseEntity {

    public String yourOffer;

    public String wantInReturn;

    public String swapDetails;

    public String swapDuration;

    public String associatedDeposit;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

}
