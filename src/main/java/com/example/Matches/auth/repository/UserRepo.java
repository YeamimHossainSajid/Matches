package com.example.Matches.auth.repository;

import com.example.Matches.auth.dto.response.CustomUserResponseDTO;
import com.example.Matches.auth.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long > {

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<CustomUserResponseDTO> findUserProjectionById(@Param("userId") Long userId);


    User findByUsername(String username);

    @EntityGraph( attributePaths = { "roles" } )
    User findByUsernameOrEmail(String username, String email );

    @Query("""
            SELECT u FROM User u where u.username=:username
            """)
    CustomUserResponseDTO searchByUsername(String username );

    boolean existsByEmail( String email );

    @EntityGraph( attributePaths = { "roles" } )
    @Query( """
                SELECT
                    user
                FROM
                    User user
                WHERE
                    user.id = :id
            """ )
    CustomUserResponseDTO findUserByUserId(@Param( "id" ) Long id );

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.roles " +
            "LEFT JOIN FETCH u.profile " +
            "LEFT JOIN FETCH u.reviewsReceived r " +
            "LEFT JOIN FETCH r.reviewer rev " +
            "LEFT JOIN FETCH rev.profile " +
            "WHERE u.id = :userId")
    User findUserWithDetailsById(@Param("userId") Long userId);


}
