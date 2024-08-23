package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItemEntity, String>{
    @Query("SELECT c FROM CartItemEntity c WHERE c.cart.id = ?1")
    Optional<CartItemEntity> findByCartId(String cartId);
}
