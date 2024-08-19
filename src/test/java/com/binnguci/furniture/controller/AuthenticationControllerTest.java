package com.binnguci.furniture.controller;

import com.binnguci.furniture.controller.authentication.AuthenticationController;
import com.binnguci.furniture.dto.request.AuthenticationRequest;
import com.binnguci.furniture.dto.request.LogoutRequest;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.dto.response.JwtResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.service.authentication.IAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private IAuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private AuthenticationRequest authenticationRequest;
    private JwtResponse jwtResponse;
    private LogoutRequest logoutRequest;

    @BeforeEach
    void setUp() {
        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("testUser");
        authenticationRequest.setPassword("testPass");

        jwtResponse = new JwtResponse("mockedJwtToken", "Bearer", 3600L);

        logoutRequest = new LogoutRequest();
        logoutRequest.setToken("mockedJwtToken");
    }

    @Test
    public void testAuthenticateSuccess() {
        when(authenticationService.login(any(AuthenticationRequest.class))).thenReturn(jwtResponse);

        ResponseEntity<APIResponse<JwtResponse>> response = authenticationController.authenticate(authenticationRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.SUCCESS.getCode(), response.getBody().getCode());
        assertEquals(ErrorCode.SUCCESS.getMessage(), response.getBody().getMessage());
        assertEquals(jwtResponse.getToken(), response.getBody().getResult().getToken());
        assertEquals(jwtResponse.getType(), response.getBody().getResult().getType());
        assertEquals(jwtResponse.getExpiredTime(), response.getBody().getResult().getExpiredTime());

        verify(authenticationService).login(authenticationRequest);
    }

    @Test
    public void testAuthenticateInvalidCredentials() {
        when(authenticationService.login(authenticationRequest)).thenReturn(null);

        ResponseEntity<APIResponse<JwtResponse>> response = authenticationController.authenticate(authenticationRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.INVALID_REQUEST.getCode(), response.getBody().getCode());
        assertEquals(ErrorCode.INVALID_REQUEST.getMessage(), response.getBody().getMessage());
        assertNull(response.getBody().getResult());

        verify(authenticationService).login(authenticationRequest);
    }

    @Test
    public void testLogoutSuccess() {
        doNothing().when(authenticationService).logout(any(LogoutRequest.class));

        ResponseEntity<APIResponse<Void>> response = authenticationController.logout(logoutRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(ErrorCode.SUCCESS.getCode(), response.getBody().getCode());
        assertEquals(ErrorCode.SUCCESS.getMessage(), response.getBody().getMessage());

        verify(authenticationService).logout(logoutRequest);
    }

    @Test
    public void testLogoutFailure() {
        doThrow(new RuntimeException("Logout failed")).when(authenticationService).logout(any(LogoutRequest.class));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticationController.logout(logoutRequest);
        });

        assertEquals("Logout failed", exception.getMessage());
        verify(authenticationService).logout(logoutRequest);
    }
}
