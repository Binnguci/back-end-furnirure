package com.binnguci.furniture.controller.user;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.dto.request.RegisterRequest;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
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
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserDTO>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        log.info("Request to register user with username: {}", registerRequest.getUsername());
        UserDTO userDTO = userService.register(registerRequest);
        if (userDTO != null) {
            log.info("Successfully registered user with username: {}", registerRequest.getUsername());
            APIResponse<UserDTO> response = APIResponse.<UserDTO>builder()
                    .code(ErrorCode.CREATE_SUCCESS.getCode())
                    .message(ErrorCode.CREATE_SUCCESS.getMessage())
                    .result(userDTO)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            log.info("Failed to register user with username: {}", registerRequest.getUsername());
            APIResponse<UserDTO> response = APIResponse.<UserDTO>builder()
                    .code(ErrorCode.CREATE_FAILED.getCode())
                    .message(ErrorCode.CREATE_FAILED.getMessage())
                    .build();
            return ResponseEntity.ok(response);
        }
    }
}
