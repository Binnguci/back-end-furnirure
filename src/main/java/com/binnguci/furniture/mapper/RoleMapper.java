package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.RoleDTO;
import com.binnguci.furniture.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDTO(RoleEntity roleEntity);

    RoleEntity toEntity(RoleDTO roleDTO);
}
