package com.binnguci.furniture.mapper;

import com.binnguci.furniture.domain.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.UserEntity;
import com.binnguci.furniture.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);

    UserEntity toRequestToEntity(RegisterRequest registerRequest);
}
