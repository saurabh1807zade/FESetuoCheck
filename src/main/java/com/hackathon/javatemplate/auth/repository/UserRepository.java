package com.hackathon.javatemplate.auth.repository;


import com.hackathon.javatemplate.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByUserTypeAndIsDisableAndUserNameOrEmailOrPhoneOrderByUpdatedAtDesc(String userType, Boolean isDisable, String username, String email, String phone);

    User findByUserNameAndIsDisable(String username, Boolean isDisable);

    boolean existsByUserName(String username);

    User findByUserName(String userName);

    User findByIdAndIsDisable(UUID id, boolean b);

    UUID findIdByUserName(String userName);
}