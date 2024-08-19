package com.binnguci.furniture.service.product;

import com.binnguci.furniture.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> findByName(String name);
}
