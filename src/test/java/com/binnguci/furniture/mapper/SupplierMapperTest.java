package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.SupplierDTO;
import com.binnguci.furniture.entity.SupplierEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplierMapperTest {

    private final SupplierMapper supplierMapper = Mappers.getMapper(SupplierMapper.class);

    @Test
    public void testToDTO() {
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setId(1);
        supplierEntity.setName("Supplier X");

        SupplierDTO supplierDTO = supplierMapper.toDTO(supplierEntity);

        assertEquals(supplierEntity.getId(), supplierDTO.getId());
        assertEquals(supplierEntity.getName(), supplierDTO.getName());
    }

    @Test
    public void testToEntity() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(1);
        supplierDTO.setName("Supplier X");

        SupplierEntity supplierEntity = supplierMapper.toEntity(supplierDTO);

        assertEquals(supplierDTO.getId(), supplierEntity.getId());
        assertEquals(supplierDTO.getName(), supplierEntity.getName());
    }
}
