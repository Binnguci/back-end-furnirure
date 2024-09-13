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
    Integer id;
    String username;
    String fullName;
    String email;
    String phone;
    String address;
    Short enabled;
    String oauth2Id;
    String oauth2Email;
    String oauth2Provider;
    String oauth2ProfilePicture;
    String otp;
    Instant otpExpiry;
    Instant createdAt;
    Instant updatedAt;
    RoleDTO role;
    CartDTO cart;
    Set<OrderDTO> orders;
    Set<WishlistDTO> wishlist;
}