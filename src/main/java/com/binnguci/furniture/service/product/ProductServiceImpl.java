package com.binnguci.furniture.service.product;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.request.ProductSearchRequest;
import com.binnguci.furniture.entity.ProductEntity;
import com.binnguci.furniture.mapper.ProductMapper;
import com.binnguci.furniture.repository.IProductRepository;
import com.binnguci.furniture.repository.custom.product.IProductRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IProductRepositoryCustom productRepositoryCustom;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest) {
        List<ProductEntity> productEntity = productRepositoryCustom.findAllMultiField(productSearchRequest);
        return productRepositoryCustom.findAllMultiField(productSearchRequest)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO updateAndSave(ProductDTO productDTO) {
        return productMapper.toDTO(
                productRepository.save(
                        productMapper.toEntity(productDTO)
                )
        );
    }

}
