package com.binnguci.furniture.service.product;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.mapper.ProductMapper;
import com.binnguci.furniture.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        return productRepository.findByNameContaining(name)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}
