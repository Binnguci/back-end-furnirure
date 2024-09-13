package com.binnguci.furniture.jwt;

import com.binnguci.furniture.config.UserDetailServiceImpl;
import com.binnguci.furniture.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailServiceImpl userDetailService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.info("Processing request to '{}'", request.getRequestURI());
        String jwtToken = getJwtTokenFromRequest(request);

        if (jwtToken != null) {
            String username = extractUsernameFromToken(jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                authenticateUser(request, username, jwtToken);
            }
        }
        chain.doFilter(request, response);
    }

    private String getJwtTokenFromRequest(HttpServletRequest request) {
        log.info("Getting JWT Token from request");
        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring("Bearer ".length());
        } else {
            log.warn("JWT Token does not begin with Bearer String");
            return null;
        }
    }

    private String extractUsernameFromToken(String token) {
        log.info("Extracting username from JWT Token");
        try {
            return jwtTokenUtil.extractUsername(token);
        } catch (IllegalArgumentException e) {
            log.error("Unable to get JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.error("JWT Token has expired", e);
        }
        return null;
    }

    private void authenticateUser(HttpServletRequest request, String username, String token) {
        log.info("Authenticating user '{}'", username);
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        if (jwtTokenUtil.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            log.info("User '{}' authenticated successfully", username);
        }
    }
}
