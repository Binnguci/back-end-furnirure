package com.binnguci.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.binnguci.furniture.entity.ReviewEntity;
import com.binnguci.furniture.dto.ReviewDTO;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDTO toDTO(ReviewEntity reviewEntity);

    ReviewEntity toEntity(ReviewDTO reviewDTO);
}
