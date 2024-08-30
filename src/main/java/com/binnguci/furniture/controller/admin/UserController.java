package com.binnguci.furniture.controller.admin;

import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping()
    public ResponseEntity<APIResponse<List<UserDTO>>> getAllUsers() {
        log.info("Request to get all users");
        List<UserDTO> users = userService.findAll();
        return buildResponse(users, (users != null) ? ErrorCode.SUCCESS : ErrorCode.NOT_FOUND);
    }

    @GetMapping("/get-information")
    public ResponseEntity<APIResponse<UserDTO>> getInformationOfUser(String username) {
        log.info("Request to get information of user with username: {}", username);
        UserDTO userDTO = userService.getInformationOfUser(username);
        return buildResponse(userDTO, userDTO != null ? ErrorCode.SUCCESS : ErrorCode.NOT_FOUND);
    }

    @GetMapping("/find-by-username")
    public ResponseEntity<APIResponse<UserDTO>> findByUsername(String username) {
        log.info("Request to find user by username: {}", username);
        UserDTO userDTO = userService.findByUsername(username);
        return buildResponse(userDTO, userDTO != null ? ErrorCode.SUCCESS : ErrorCode.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> blockAndUnBlockUser(@PathVariable Integer id) {
        log.info("Request to block user with id: {}", id);
        userService.blockAndUnBlockUser(id);
        return buildResponse(null, ErrorCode.SUCCESS);
    }


    private <T> ResponseEntity<APIResponse<T>> buildResponse(T result, ErrorCode errorCode) {
        APIResponse<T> response = APIResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(result)
                .build();
        return ResponseEntity.ok(response);
    }
}
