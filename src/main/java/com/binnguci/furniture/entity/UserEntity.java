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
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstant.USER_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String email;
    String phone;
    String address;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "otp")
    String otp;
    @Column(name = "otp_expired")
    Instant otpExpired;
    @Column(name = "oauth2_id")
    String oauth2Id;
    @Column(name = "oauth2_email")
    String oauth2Email;
    @Column(name = "oauth2_provider")
    String oauth2Provider;
    @Column(name = "oauth2_profile_picture")
    String oauth2ProfilePicture;
    @Column(name = "is_active", nullable = false)
    Boolean isActive = true;
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

}
