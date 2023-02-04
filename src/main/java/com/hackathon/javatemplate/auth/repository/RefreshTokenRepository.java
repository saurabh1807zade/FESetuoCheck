package com.hackathon.javatemplate.auth.repository;


import com.hackathon.javatemplate.auth.entities.RefreshToken;
import com.hackathon.javatemplate.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByRefreshTokenAndUser_UserType(String refreshToken, String userType);

    int deleteByUser(User user);

}