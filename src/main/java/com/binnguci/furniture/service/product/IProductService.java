package com.binnguci.furniture.service.product;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.request.ProductSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    ProductDTO findById(Integer id);
    Page<ProductDTO> findAll(Pageable pageable);
    List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest);
    ProductDTO updateAndSave(ProductDTO productDTO);
    ProductDTO delete(Integer id);
}
