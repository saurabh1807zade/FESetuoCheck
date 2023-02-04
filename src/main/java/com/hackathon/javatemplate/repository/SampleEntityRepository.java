package com.hackathon.javatemplate.repository;

import com.hackathon.javatemplate.entities.SampleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SampleEntityRepository extends JpaRepository<SampleEntity, UUID> {

    @Query("SELECT e " +
            "FROM SampleEntity e " +
            "JOIN user_table u " +
            "ON e.userId = u.id " +
            "AND u.isDisable = false ")
    SampleEntity findByUserIdAndIsDisable(UUID id, boolean b);

    @Query("SELECT e " +
            "FROM SampleEntity e " +
            "JOIN user_table u " +
            "ON e.userId = u.id " +
            "WHERE LOWER(e.firstName) LIKE %:search% " +
            "OR LOWER(e.lastName) LIKE %:search% " +
            "AND u.isDisable = false ")
    List<SampleEntity> getAllBySearch(String search, Pageable defaultSortingAndPaging);

    @Query("SELECT COUNT(e) " +
            "FROM SampleEntity e " +
            "JOIN user_table u " +
            "ON e.userId = u.id " +
            "WHERE (LOWER(e.firstName) LIKE (%:search%) " +
            "OR LOWER(e.lastName) LIKE (%:search%) ) " +
            "AND u.isDisable = false ")
    Integer getCountBySearch(String search);

    @Query("SELECT e " +
            "FROM SampleEntity e " +
            "JOIN user_table u " +
            "ON e.userId = u.id " +
            "WHERE u.isDisable = false " +
            "AND e.firstName LIKE %:firstName% ")
    List<SampleEntity> getAllAndByFilter(String firstName, Pageable defaultSortingAndPaging);

    @Query("SELECT COUNT(e) " +
            "FROM SampleEntity e " +
            "JOIN user_table u " +
            "ON u.id = e.userId " +
            "WHERE u.isDisable = false " +
            "AND e.firstName LIKE %:firstName% ")
    Integer getAllAndFilterCount(String firstName);


}
