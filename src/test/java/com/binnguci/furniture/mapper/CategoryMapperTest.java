package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.CategoryDTO;
import com.binnguci.furniture.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    public void testToDTO() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);
        categoryEntity.setName("Furniture");

        CategoryDTO categoryDTO = categoryMapper.toDTO(categoryEntity);

        assertEquals(categoryEntity.getId(), categoryDTO.getId());
        assertEquals(categoryEntity.getName(), categoryDTO.getName());
    }

    @Test
    public void testToEntity() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1);
        categoryDTO.setName("Furniture");

        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);

        assertEquals(categoryDTO.getId(), categoryEntity.getId());
        assertEquals(categoryDTO.getName(), categoryEntity.getName());
    }
}
