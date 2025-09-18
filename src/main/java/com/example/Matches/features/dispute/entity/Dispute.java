package com.example.Matches.features.dispute.entity;

import com.example.Matches.generic.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Dispute extends BaseEntity {

    private String yourStatement;

    private String file;



}
