package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.WishlistEntity;
import com.binnguci.furniture.dto.WishlistDTO;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    WishlistDTO toDTO(WishlistEntity wishlistEntity);

    WishlistEntity toEntity(WishlistDTO wishlistDTO);
}
