package com.example.Matches.features.proposeswap.repository;

import com.example.Matches.auth.model.User;
import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.generic.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposeSwapRepository extends JpaRepository<ProposeSwap, Long> {
    List<ProposeSwap> findByReceiverAndStatus(User receiver, RequestStatus status);
    List<ProposeSwap> findBySender(User sender);

}
