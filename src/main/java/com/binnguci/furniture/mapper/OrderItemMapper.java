package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.OrderItemEntity;
import com.binnguci.furniture.dto.OrderItemDTO;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO toDTO(OrderItemEntity orderItemEntity);

    OrderItemEntity toEntity(OrderItemDTO orderItemDTO);
}
