package com.binnguci.furniture.controller.user;

import com.binnguci.furniture.domain.request.RegisterRequest;
import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.domain.response.ResponseBuilder;
import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.service.account.IAccountService;
import com.binnguci.furniture.service.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("userUserController")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final IAccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserDTO>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        log.info("Request to register user with username: {}", registerRequest.getUsername());
        UserDTO userDTO = accountService.register(registerRequest);
        return ResponseBuilder.buildResponse(userDTO, userDTO != null ? ErrorCode.CREATE_SUCCESS : ErrorCode.CREATE_FAILED);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<APIResponse<String>> forgetPassword(@RequestBody String email) {
        log.info("Request to forget password for email: {}", email);
        accountService.forgetPassword(email);
        return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<APIResponse<String>> changePassword(@RequestBody String email, @RequestBody String password) {
        log.info("Request to change password for email: {}", email);
        accountService.changePassword(email, password);
        return ResponseBuilder.buildResponse(null, ErrorCode.SUCCESS);
    }

    @GetMapping("/get-information")
    public ResponseEntity<APIResponse<UserDTO>> getInformationOfUser(@RequestParam String username) {
        log.info("Request to get information of user with username: {}", username);
        UserDTO userDTO = userService.getInformationOfUser(username);
        return ResponseBuilder.buildResponse(userDTO, userDTO != null ? ErrorCode.SUCCESS : ErrorCode.NOT_FOUND);
    }

    @PatchMapping("/update-information")
    public ResponseEntity<APIResponse<UserDTO>> updateInformationOfUser(@RequestBody UserDTO userDTO) {
        log.info("Request to update information of user with username: {}", userDTO.getUsername());
        UserDTO updatedUserDTO = userService.updateInformationOfUser(userDTO);
        return ResponseBuilder.buildResponse(updatedUserDTO, updatedUserDTO != null ? ErrorCode.SUCCESS : ErrorCode.UPDATE_FAILED);
    }

}

