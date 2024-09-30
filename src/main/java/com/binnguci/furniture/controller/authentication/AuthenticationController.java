package com.binnguci.furniture.controller.authentication;

import com.binnguci.furniture.domain.request.AccountVerifyRequest;
import com.binnguci.furniture.domain.request.AuthenticationRequest;
import com.binnguci.furniture.domain.request.LogoutRequest;
import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.domain.response.JwtResponse;
import com.binnguci.furniture.domain.response.ResponseBuilder;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.account.IAccountService;
import com.binnguci.furniture.service.authentication.IAuthenticationService;
import com.binnguci.furniture.service.user.IUserService;
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
    private final IUserService userService;
    private final IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JwtResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        log.info("Login request: {}", request);
        JwtResponse jwtObj = authenticationService.login(request);
        if (jwtObj != null) {
            return ResponseBuilder.buildResponse(jwtObj, ErrorCode.SUCCESS);
        } else {
            return ResponseBuilder.buildResponse(null, ErrorCode.INVALID_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<APIResponse<Void>> logout(@RequestBody @Valid LogoutRequest request) {
        log.info("Logout request: {}", request);
        try{
            authenticationService.logout(request);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(null, ErrorCode.INVALID_REQUEST);
        }

    }

    @PostMapping("/verify-otp")
    public ResponseEntity<APIResponse<Void>> verifyOtp(@RequestBody @Valid AccountVerifyRequest accountVerifyRequest) {
        log.info("OTP verification request for email: {}", accountVerifyRequest.getEmail());

        try {
            accountService.verifyAccount(accountVerifyRequest);
            return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
        } catch (AppException ex) {
            log.error("OTP verification failed for email: {}", accountVerifyRequest.getEmail(), ex);
            return ResponseBuilder.buildResponse(null, ex.getErrorCode());
        }
    }

}
