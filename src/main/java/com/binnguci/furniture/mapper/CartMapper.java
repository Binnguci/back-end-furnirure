package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.CartEntity;
import com.binnguci.furniture.dto.CartDTO;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(target = "cartItems", source = "cartItems")
    CartDTO toDTO(CartEntity cartEntity);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(target = "cartItems", source = "cartItems")
    CartEntity toEntity(CartDTO cartDTO);
}
