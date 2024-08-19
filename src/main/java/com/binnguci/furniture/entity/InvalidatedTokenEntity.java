package com.binnguci.furniture.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "invalidated_tokens")
public class InvalidatedTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tokenId;
    private Date expired;
}
