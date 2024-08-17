package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.CartEntity;
import com.binnguci.furniture.dto.CartDTO;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDTO toDTO(CartEntity cartEntity);

    CartEntity toEntity(CartDTO cartDTO);
}
