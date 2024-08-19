package com.binnguci.furniture.repository;

import com.binnguci.furniture.entity.ProductEntity;
import com.binnguci.furniture.repository.custom.product.IProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {

}
