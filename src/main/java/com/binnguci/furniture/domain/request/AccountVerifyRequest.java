package com.binnguci.furniture.domain.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVerifyRequest {
    private String email;
    private String otp;
}
