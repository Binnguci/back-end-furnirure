package com.binnguci.furniture.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        log.warn("Unauthorized request to '{}'. Reason: {}", request.getRequestURI(), authException.getMessage());
        prepareErrorResponse(response, HttpStatus.UNAUTHORIZED, authException.getMessage());
    }

    private void prepareErrorResponse(HttpServletResponse response, HttpStatus status, String message)
            throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = Map.of(
                "error", status.getReasonPhrase(),
                "message", message,
                "statusCode", status.value()
        );

        String responseBody = objectMapper.writeValueAsString(body);
        log.info("Responding with error: {}", responseBody);

        response.getWriter().write(responseBody);
    }
}
