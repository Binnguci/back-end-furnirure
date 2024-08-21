package com.binnguci.furniture.controller.authentication;

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
        log.info("Login request: {}", request);
        JwtResponse jwtObj = authenticationService.login(request);
        if (jwtObj != null) {
            return buildResponse(jwtObj, ErrorCode.SUCCESS);
        } else {
            return buildResponse(null, ErrorCode.INVALID_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody @Valid LogoutRequest request) {
        log.info("Logout request: {}", request);
        try{
            authenticationService.logout(request);
            return buildResponse(null, ErrorCode.SUCCESS);
        } catch (Exception e) {
            return buildResponse(null, ErrorCode.INVALID_REQUEST);
        }

    }


    private <T> ResponseEntity<APIResponse<T>> buildResponse(T result, ErrorCode errorCode) {
        log.info("Response: {}", errorCode);
        APIResponse<T> response = APIResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(result)
                .build();
        return ResponseEntity.ok(response);
    }


}
