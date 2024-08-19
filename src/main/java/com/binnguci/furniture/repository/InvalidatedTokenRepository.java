package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.InvalidatedTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedTokenEntity, String>{
    boolean existsById(String id);
}
