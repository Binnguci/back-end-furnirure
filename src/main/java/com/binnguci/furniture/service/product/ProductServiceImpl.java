package com.binnguci.furniture.service.product;

import com.binnguci.furniture.domain.request.ProductSearchRequest;
import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.entity.ProductEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.ProductMapper;
import com.binnguci.furniture.repository.IProductRepository;
import com.binnguci.furniture.repository.custom.product.IProductRepositoryCustom;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements IProductService {
    IProductRepository productRepository;
    IProductRepositoryCustom productRepositoryCustom;
    ProductMapper productMapper;

    @Override
    public ProductDTO findById(Integer id) {
        log.info("Request to get product with id: {}", id);
        return productRepository.findById(id).map(productMapper::toDTO).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.info("Request to get all products");
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        return productPage.map(productMapper::toDTO);
    }

    @Override
    public List<ProductDTO> findByMultiFields(ProductSearchRequest productSearchRequest) {
        log.info("Request to search product by name: {}", productSearchRequest.getName());
        return productRepositoryCustom.findAllMultiField(productSearchRequest).stream().map(productMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public ProductDTO updateAndSave(ProductDTO productDTO) {
        log.info("Request to update product with id: {}", productDTO.getId());
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        if (productEntity.getId() == null) {
            log.info("Successfully to create product");
            productEntity.setCreatedAt(Instant.now());

        } else {
            log.info("Successfully to update product");
            productEntity.setUpdatedAt(Instant.now());
        }
        log.info("Successfully updated or create product with id: {}", productDTO.getId());
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDTO(savedEntity);
    }

    @Override
    public ProductDTO delete(Integer id) {
        log.info("Request to delete product with id: {}", id);
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        productEntity.setIsActive((short) 0);
        ProductDTO productDTO = productMapper.toDTO(productRepository.save(productEntity));
        log.info("Successfully deleted product with id: {}", id);
        return productDTO;
    }

}
