package com.binnguci.furniture.mapper;


import com.binnguci.furniture.dto.CategoryDTO;
import com.binnguci.furniture.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryDTO categoryDTO);
}
