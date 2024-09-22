package com.binnguci.furniture.entity;

import com.binnguci.furniture.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.CART_ITEMS_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    CartEntity cart;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    ProductEntity product;
}
