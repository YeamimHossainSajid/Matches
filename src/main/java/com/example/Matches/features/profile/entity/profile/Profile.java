package com.example.Matches.features.profile.entity.profile;

import com.example.Matches.generic.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile extends BaseEntity {

    private String fullName;

    private String phoneNumber;

    private String location;

    private String bio;

    public String imageUrl;

    public String websiteUrl;

    @ElementCollection
    @CollectionTable(name = "profile_skills", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "skill")
    private List<String> skills;

}
