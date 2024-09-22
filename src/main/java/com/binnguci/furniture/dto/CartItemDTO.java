package com.binnguci.furniture.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDTO {
     String id;
     Integer quantity;
     ProductDTO product;
}
