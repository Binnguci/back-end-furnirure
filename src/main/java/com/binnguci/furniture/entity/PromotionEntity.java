package com.binnguci.furniture.entity;

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
@Table(name = "promotion")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String name;
    String description;
    String code;
    Double discount;
    @Column(name = "min_order")
    Double minOrder;
    @Column(name = "max_discount")
    Double maxDiscount;
    @Column(name = "start_date")
    Long startDate;
    @Column(name = "end_date")
    Long endDate;
    @Column(name = "is_active")
    Boolean isActive;
    @Column(name = "is_deleted")
    Boolean isDeleted;
}
