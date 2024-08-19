package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsUserById(Integer id);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByEmailAndOTO(String email, String otp);
    Optional<UserEntity> findByOTP(String otp);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUsernameAndEmail(String username, String email);
}
