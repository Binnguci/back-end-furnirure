package com.binnguci.furniture.entity;

import com.binnguci.furniture.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.PRODUCT_TABLE, indexes = {
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_is_active", columnList = "is_active"),
        @Index(name = "idx_product_category", columnList = "category_id"),
        @Index(name = "idx_product_supplier", columnList = "supplier_id"),
        @Index(name = "idx_product_price", columnList = "price")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Double price;
    Integer stock;
    String description;
    @Column(name = "is_active")
    Short isActive;
    String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    SupplierEntity supplier;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<CartItemEntity> cartItems = new HashSet<>();
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ReviewEntity> reviews = new HashSet<>();
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<OrderItemEntity> orderItems = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<WishlistEntity> wishlist;
}
