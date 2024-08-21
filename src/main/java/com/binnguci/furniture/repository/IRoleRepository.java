package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
}
