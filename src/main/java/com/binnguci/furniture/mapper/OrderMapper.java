package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.OrderEntity;
import com.binnguci.furniture.dto.OrderDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDTO(OrderEntity orderEntity);

    OrderEntity toEntity(OrderDTO orderDTO);
}
