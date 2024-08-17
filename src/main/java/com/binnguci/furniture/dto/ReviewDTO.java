package com.binnguci.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Integer id;
    private String comment;
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductDTO product;
    private UserDTO user;
}