package com.binnguci.furniture.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLogDTO {
    Long id;
    String level;
    String ip;
    String action;
    Instant createdAt;
    Instant updatedAt;
    UserDTO user;
}
