package com.binnguci.furniture.entity;

import com.binnguci.furniture.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstant.USER_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String email;
    String phone;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "otp")
    Integer otp;
    @Column(name = "otp_expired")
    Instant otpExpired;
    @Column(name = "oauth2_id")
    String oauth2Id;
    @Column(name = "oauth2_provider")
    String oauth2Provider;
    @Column(name = "is_active", nullable = false)
    Short isActive;
    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleEntity role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<UserLogEntity> logs = new HashSet<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    CartEntity cart;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OrderEntity> orders = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<WishlistEntity> wishlist;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<ReviewEntity> reviews = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<AddressEntity> addresses = new HashSet<>();
}
