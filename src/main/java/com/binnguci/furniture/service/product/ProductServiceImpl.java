package com.binnguci.furniture.service.product;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.dto.request.ProductSearchRequest;
import com.binnguci.furniture.entity.ProductEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
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
        log.info("Request to get all products");
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest) {
        log.info("Request to search product by name: {}", productSearchRequest.getName());
        return productRepositoryCustom.findAllMultiField(productSearchRequest)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO updateAndSave(ProductDTO productDTO) {
        log.info("Request to update product with id: {}", productDTO.getId());
        return productMapper.toDTO(
                productRepository.save(
                        productMapper.toEntity(productDTO)
                )
        );
    }

    @Override
    public ProductDTO delete(Integer id) {
        log.info("Request to delete product with id: {}", id);
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        productEntity.setIsActive((short)0);
        ProductDTO productDTO = productMapper.toDTO(productRepository.save(productEntity));
        return productDTO;
    }

}
