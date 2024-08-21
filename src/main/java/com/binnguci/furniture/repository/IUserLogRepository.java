package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.UserLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLogRepository extends JpaRepository<UserLogEntity, Long>{
}
