package com.binnguci.furniture.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDTO {
    String addressLine;
    String ward;
    String district;
    String province;
    String country;
    Boolean isDefault;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
