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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstant.PROMOTION_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String description;
    String code;
    @Column(name = "discount_percent")
    Double discountPercent;
    @Column(name = "min_order")
    Double minOrder;
    @Column(name = "max_discount_amount")
    Double maxDiscountAcount;
    @Column(name = "start_date")
    Long startDate;
    @Column(name = "end_date")
    Long endDate;
    @Column(name = "is_active")
    Boolean isActive = true;
    @Column(name = "usage_limit")
    Integer usageLimit;
    @ManyToMany
    @JoinTable(
            name = "promotion_orders",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    Set<OrderEntity> orders = new HashSet<>();

}
