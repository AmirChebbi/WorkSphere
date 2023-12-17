package com.WorkSphere.WorkSphere.Repositories;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select u from UserEntity u order by u.id")
    List<UserEntity> fetchAllUsers(Pageable pageable);

    @Query(value = "select count (u) from UserEntity u")
    long getTotalUserCount();

    @Query("SELECT U FROM UserEntity U " +
            "WHERE (COALESCE(:fullName, U.firstName) IS NULL OR LOWER(U.firstName) LIKE CONCAT('%', LOWER(:firstName), '%')) " +
            "AND (COALESCE(:lastName, U.lastName) IS NULL OR LOWER(U.lastName) LIKE CONCAT('%', LOWER(:lastName), '%')) " +
            "AND (COALESCE(:email, U.email) IS NULL OR LOWER(U.email) LIKE CONCAT('%', LOWER(:email), '%')) " +
            "AND (COALESCE(:phoneNumber, U.phoneNumber) IS NULL OR LOWER(U.phoneNumber) LIKE CONCAT('%', LOWER(:phoneNumber), '%')) " +
            "AND U.role.name != 'ADMIN'")
    List<UserEntity> searchUsers(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber
    );

    @Query(value = "select exists (select u from UserEntity u where u.email = :email) AS RESULT")
    boolean isEmailRegistered(@Param("email")String email);

    @Query(value = "select exists (select u from UserEntity u where u.phoneNumber = :phoneNumber) AS RESULT")
    boolean isPhoneNumberRegistered(@Param("phoneNumber")String phoneNumber);

    @Query(value = "SELECT U FROM UserEntity  U WHERE U.id = :id")
    Optional<UserEntity> fetchUserWithId(@Param("id") UUID id);

    @Query(value = "SELECT U FROM UserEntity U WHERE  U.email = :email ")
    Optional<UserEntity> fetchUserWithEmail(@Param("email") String email);}
