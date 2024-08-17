package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.UserLogDTO;
import com.binnguci.furniture.entity.UserLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface UserLogMapper {
    UserLogMapper INSTANCE = Mappers.getMapper(UserLogMapper.class);

    UserLogDTO toDTO(UserLogEntity userLogEntity);

    UserLogEntity toEntity(UserLogDTO userLogDTO);
}
