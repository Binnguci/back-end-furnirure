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
@Table(name = DatabaseConstant.ORDER_TABLE, indexes = {
        @Index(name = "idx_order_total_price", columnList = "total_price"),
        @Index(name = "idx_order_status", columnList = "status"),
        @Index(name = "idx_order_user", columnList = "user_id"),
        @Index(name = "idx_order_payment_method", columnList = "payment_method")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "total_amount")
    Double totalAmount;
    String address;
    String status;
    @Column(name = "payment")
    String payment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    Set<OrderItemEntity> orderItems = new HashSet<>();
    @ManyToMany(mappedBy = "orders")
    Set<PromotionEntity> promotions = new HashSet<>();
}

