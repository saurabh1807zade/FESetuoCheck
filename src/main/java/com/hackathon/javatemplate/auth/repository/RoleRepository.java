package com.hackathon.javatemplate.auth.repository;

import com.hackathon.javatemplate.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);

    @Query("SELECT r " +
            "FROM Role r " +
            "WHERE r.name IN :names ")
    List<Role> findByNames(@Param("names") List<String> names);

}