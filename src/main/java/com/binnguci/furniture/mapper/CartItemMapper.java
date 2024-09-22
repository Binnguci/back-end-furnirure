package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.CartItemDTO;
import com.binnguci.furniture.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDTO toDTO(CartItemEntity cartItemEntity);

    CartItemEntity toEntity(CartItemDTO cartItemDTO);
}
