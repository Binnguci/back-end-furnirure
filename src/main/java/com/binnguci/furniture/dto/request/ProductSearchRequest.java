package com.binnguci.furniture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchRequest {
    private String name;
    private String category;
    private String supplier;
    private Double minPrice;
    private Double maxPrice;
}
