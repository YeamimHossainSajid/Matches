package com.example.Matches.features.rating.entity;

import com.example.Matches.auth.model.User;
import com.example.Matches.generic.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "rating",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"rater_id", "ratee_id"})
        }
)
public class Rating extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "rater_id", nullable = false)
    private User rater;

    @ManyToOne
    @JoinColumn(name = "ratee_id", nullable = false)
    private User ratee;

    private int rating;
}
