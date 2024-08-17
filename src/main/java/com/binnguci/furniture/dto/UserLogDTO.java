package com.binnguci.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLogDTO {
    private Integer id;
    private String level;
    private String ip;
    private String action;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDTO user;
}
