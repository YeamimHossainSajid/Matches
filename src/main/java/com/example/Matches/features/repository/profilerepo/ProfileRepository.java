package com.example.Matches.features.repository.profilerepo;

import com.example.Matches.features.entity.profile.Profile;
import com.example.Matches.generic.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends AbstractRepository<Profile> {
}
