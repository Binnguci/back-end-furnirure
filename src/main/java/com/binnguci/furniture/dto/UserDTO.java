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
public class UserDTO {
    Long id;
    String username;
    String fullName;
    String email;
    String phone;
    String oauth2Id;
    String oauth2Provider;
    String otp;
    Instant otpExpired;
    Instant createdAt;
    Instant updatedAt;
    RoleDTO role;
    CartDTO cart;
    Set<OrderDTO> orders;
    Set<WishlistDTO> wishlist;
}