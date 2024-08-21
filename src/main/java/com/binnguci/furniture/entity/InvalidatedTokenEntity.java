package com.binnguci.furniture.entity;

import com.binnguci.furniture.constant.DatabaseConstant;
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
@Table(name = DatabaseConstant.INVALIDATED_TOKENS_TABLE)
public class InvalidatedTokenEntity {
    @Id
    @Column(name = "token_id")
    private String tokenId;
    private Date expired;
}
