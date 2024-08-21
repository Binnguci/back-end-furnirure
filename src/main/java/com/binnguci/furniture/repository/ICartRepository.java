package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<CartEntity, Integer>{
    @Query("SELECT c FROM CartEntity c WHERE c.user.id = :userId")
    Optional<CartEntity> findByUserId(Integer userId);
}
