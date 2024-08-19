package com.binnguci.furniture.service.product;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.request.ProductSearchRequest;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest);
    ProductDTO updateAndSave(ProductDTO productDTO);
    ProductDTO delete(Integer id);
}
