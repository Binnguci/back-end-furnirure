package com.binnguci.furniture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Short enabled;
    @Column(name = "oauth2_id")
    private String oauth2Id;
    @Column(name = "oauth2_email")
    private String oauth2Email;
    @Column(name = "oauth2_provider")
    private String oauth2Provider;
    @Column(name = "oauth2_profile_picture")
    private String oauth2ProfilePicture;
    @Column(name = "oauth2_access_token")
    private String oauth2AccessToken;
    @Column(name = "oauth2_refresh_token")
    private String oauth2RefreshToken;
    @Column(name = "oauth2_token_expiry")
    private Long oauth2TokenExpiry;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserLogEntity> logs = new HashSet<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderEntity> orders = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WishlistEntity> wishlist;
}
