package com.binnguci.furniture.controller.authentication;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.dto.request.AuthenticationRequest;
import com.binnguci.furniture.dto.request.LogoutRequest;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.dto.response.JwtResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.service.authentication.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JwtResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        JwtResponse jwtObj = authenticationService.login(request);
        if (jwtObj != null) {
            APIResponse<JwtResponse> response = APIResponse.<JwtResponse>builder()
                    .code(ErrorCode.SUCCESS.getCode())
                    .message(ErrorCode.SUCCESS.getMessage())
                    .result(jwtObj)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            APIResponse<JwtResponse> response = APIResponse.<JwtResponse>builder()
                    .code(ErrorCode.INVALID_REQUEST.getCode())
                    .message(ErrorCode.INVALID_REQUEST.getMessage())
                    .build();
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody @Valid LogoutRequest request) {
        authenticationService.logout(request);
        APIResponse<Void> response = APIResponse.<Void>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .build();
        return ResponseEntity.ok(response);
    }

}
