package com.binnguci.furniture.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDTO {
     Long id;
     Integer quantity;
     Double price;
     ProductDTO product;
}