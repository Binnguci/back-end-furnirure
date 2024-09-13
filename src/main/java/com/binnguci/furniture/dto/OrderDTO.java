package com.binnguci.furniture.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {
     Long id;
     Double totalPrice;
     Instant createdAt;
     Instant updatedAt;
     String address;
     String status;
     String paymentMethod;
     UserDTO user;
     Set<OrderItemDTO> orderItems;
}