package com.binnguci.furniture.repository.custom.product;

import com.binnguci.furniture.domain.request.ProductSearchRequest;
import com.binnguci.furniture.entity.ProductEntity;

import java.util.List;

public interface IProductRepositoryCustom {
    List<ProductEntity> findAllMultiField(ProductSearchRequest productSearchRequest);
}
