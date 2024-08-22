package com.binnguci.furniture.controller;


import com.binnguci.furniture.controller.user.UserController;
import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.domain.request.RegisterRequest;
import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.service.user.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .build();

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        when(userService.register(request)).thenReturn(userDTO);

        ResponseEntity<APIResponse<UserDTO>> response = userController.register(request);
        assertNotNull(response);
        assertEquals(ErrorCode.CREATE_SUCCESS.getCode(), response.getBody().getCode());
        assertEquals("testuser", response.getBody().getResult().getUsername());

        verify(userService, times(1)).register(request);
    }

    @Test
    void testRegisterFailure() {
        RegisterRequest request = RegisterRequest.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .build();

        when(userService.register(request)).thenReturn(null);

        ResponseEntity<APIResponse<UserDTO>> response = userController.register(request);
        assertNotNull(response);
        assertEquals(ErrorCode.CREATE_FAILED.getCode(), response.getBody().getCode());
        assertNull(response.getBody().getResult());

        verify(userService, times(1)).register(request);
    }
}
