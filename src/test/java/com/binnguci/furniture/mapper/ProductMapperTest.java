package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.ProductDTO;
import com.binnguci.furniture.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void testToDTO() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("Product X");
        productEntity.setDescription("Description of Product X");
        productEntity.setPrice(100.0);
        productEntity.setStock(50);

        ProductDTO productDTO = productMapper.toDTO(productEntity);

        assertEquals(productEntity.getId(), productDTO.getId());
        assertEquals(productEntity.getName(), productDTO.getName());
        assertEquals(productEntity.getDescription(), productDTO.getDescription());
        assertEquals(productEntity.getPrice(), productDTO.getPrice());
        assertEquals(productEntity.getStock(), productDTO.getStock());
    }

    @Test
    public void testToEntity() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("Product X");
        productDTO.setDescription("Description of Product X");
        productDTO.setPrice(100.0);
        productDTO.setStock(50);

        ProductEntity productEntity = productMapper.toEntity(productDTO);

        assertEquals(productDTO.getId(), productEntity.getId());
        assertEquals(productDTO.getName(), productEntity.getName());
        assertEquals(productDTO.getDescription(), productEntity.getDescription());
        assertEquals(productDTO.getPrice(), productEntity.getPrice());
        assertEquals(productDTO.getStock(), productEntity.getStock());
    }
}
