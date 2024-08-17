package com.binnguci.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private Short enabled;
    private String oauth2Id;
    private String oauth2Email;
    private String oauth2Provider;
    private String oauth2ProfilePicture;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<RoleDTO> roles;
    private Set<OrderDTO> orders;
    private Set<WishlistDTO> wishlist;
}