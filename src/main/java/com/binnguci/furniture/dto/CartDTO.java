package com.binnguci.furniture.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {
    Integer id;
    Integer quantity;
    UserDTO user;
    Set<CartItemDTO> cartItems;
}