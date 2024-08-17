package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.ProductEntity;
import com.binnguci.furniture.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(ProductEntity productEntity);

    ProductEntity toEntity(ProductDTO productDTO);
}
