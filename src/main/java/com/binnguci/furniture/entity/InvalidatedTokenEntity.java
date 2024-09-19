package com.binnguci.furniture.entity;

import com.binnguci.furniture.constant.DatabaseConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DatabaseConstant.INVALIDATED_TOKENS_TABLE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedTokenEntity {
    @Id
    @Column(name = "token_id")
    String tokenId;
    Date expired;
}
