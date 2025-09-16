package com.example.Matches.features.proposeswap.repository;

import com.example.Matches.auth.model.User;
import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.features.proposeswap.payload.response.ProposeSwapResponseDto;
import com.example.Matches.generic.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposeSwapRepository extends JpaRepository<ProposeSwap, Long> {

    List<ProposeSwap> findByReceiverAndStatus(User receiver, RequestStatus status);

    List<ProposeSwap> findBySender(User sender);

    @Query("SELECT p FROM ProposeSwap p " +
            "WHERE (p.sender = :user OR p.receiver = :user) " +
            "AND p.status IN :statuses")
    List<ProposeSwap> findByUserAndStatuses(
            @Param("user") User user,
            @Param("statuses") List<RequestStatus> statuses
    );

    @Query("SELECT p.status AS status, COUNT(p) AS count " +
            "FROM ProposeSwap p " +
            "WHERE (p.sender = :user OR p.receiver = :user) " +
            "GROUP BY p.status")
    List<Object[]> countByUserGroupByStatus(@Param("user") User user);

}
