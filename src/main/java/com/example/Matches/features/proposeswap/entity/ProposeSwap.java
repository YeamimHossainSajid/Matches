package com.example.Matches.features.proposeswap.entity;

import com.example.Matches.generic.model.BaseEntity;
import jakarta.persistence.Access;
import jakarta.persistence.Entity;
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


}
