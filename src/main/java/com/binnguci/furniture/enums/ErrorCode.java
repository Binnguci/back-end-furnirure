package com.binnguci.furniture.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    DELETE_SUCCESS(200, "Delete success"),
    DELETE_FAILED(400, "Delete failed"),
    SUCCESS(200, "Success"),
    INVALID_REQUEST(400, "Invalid request"),
    PARAMETER_NOT_VALID(400, "Parameter not valid"),
    USER_NOT_FOUND(404, "User not found"),
    VALIDATION_ERROR(400, "Validation error"),
    PARAMETER_MISSING(400, "Parameter missing"),
    CREATE_SUCCESS(201, "Create success"),
    CREATE_FAILED(400, "Create failed"),
    UPDATE_SUCCESS(200, "Update success"),
    UPDATE_FAILED(400, "Update failed"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED.value(), "Unauthenticated"),
    UNAUTHORIZED(HttpStatus.FORBIDDEN.value(), "You do not have permission"),
    NOTIFICATION_EMPTY(204, "Notification empty"),
    FOUND(200, "Found"),
    NOT_FOUND(404, "Not found"),
    EMAIL_ALREADY_EXISTS(400, "Email already exists"),
    USERNAME_ALREADY_EXISTS(400, "Username already exists"),
    INVALID_PASSWORD(400, "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"),
    ROLE_NOT_FOUND(400, "Role not found");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;
}
