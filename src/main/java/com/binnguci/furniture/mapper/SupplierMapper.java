package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.SupplierDTO;
import com.binnguci.furniture.entity.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierDTO toDTO(SupplierEntity supplierEntity);

    SupplierEntity toEntity(SupplierDTO supplierDTO);
}
