package com.binnguci.furniture.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Success
    SUCCESS(200, "Success"),
    DELETE_SUCCESS(200, "Delete success"),
    CREATE_SUCCESS(201, "Create success"),
    UPDATE_SUCCESS(200, "Update success"),
    FOUND(200, "Found"),

    // Client Errors
    INVALID_REQUEST(400, "Invalid request"),
    PARAMETER_NOT_VALID(400, "Parameter not valid or validation error"),
    PARAMETER_MISSING(400, "Parameter missing"),
    DELETE_FAILED(400, "Delete failed"),
    CREATE_FAILED(400, "Create failed"),
    UPDATE_FAILED(400, "Update failed"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED.value(), "Unauthenticated"),
    UNAUTHORIZED(HttpStatus.FORBIDDEN.value(), "You do not have permission"),
    USER_NOT_FOUND(404, "User not found"),
    NOT_FOUND(404, "Not found"),
    EMAIL_ALREADY_EXISTS(409, "Email already exists"),
    USERNAME_ALREADY_EXISTS(409, "Username already exists"),
    INVALID_PASSWORD(400, "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"),
    ROLE_NOT_FOUND(404, "Role not found"),
    OTP_EXPIRED(400, "OTP expired"),
    INVALID_OTP(400, "Invalid OTP"),
    ACCOUNT_NOT_VERIFIED(400, "Account not verified"),
    CART_NOT_FOUND(400, "Cart not found"),
    CART_ITEM_NOT_FOUND(400, "Cart item not found"),

    // Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

