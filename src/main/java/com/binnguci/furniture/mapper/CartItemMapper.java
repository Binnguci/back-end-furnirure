package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.CartItemEntity;
import com.binnguci.furniture.dto.CartItemDTO;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO toDTO(CartItemEntity cartItemEntity);

    CartItemEntity toEntity(CartItemDTO cartItemDTO);
}
