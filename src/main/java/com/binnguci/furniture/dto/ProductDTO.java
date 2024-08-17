package com.binnguci.furniture.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private String description;
    private String image;
    private CategoryDTO category;
    private SupplierDTO supplier;
    private Set<CartItemDTO> cartItems;
    private Set<ReviewDTO> reviews;
}
