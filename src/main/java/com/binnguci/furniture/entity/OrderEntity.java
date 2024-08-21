package com.binnguci.furniture.entity;

import com.binnguci.furniture.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.ORDER_TABLE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String address;
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderItemEntity> orderItems = new HashSet<>();
}

