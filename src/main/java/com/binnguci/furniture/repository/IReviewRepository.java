package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
