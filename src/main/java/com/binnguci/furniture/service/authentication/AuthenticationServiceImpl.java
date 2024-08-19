package com.binnguci.furniture.service.authentication;

import com.binnguci.furniture.dto.request.AuthenticationRequest;
import com.binnguci.furniture.dto.response.JwtResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public JwtResponse login(AuthenticationRequest request) {
        log.info("Logging in user: {}", request.getUsername());
        Authentication authentication = authenticate(request.getUsername(), request.getPassword());
        log.info("User {} is authenticated: {}", request.getUsername(), authentication.isAuthenticated());
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        boolean isAuthenticated = authentication.isAuthenticated();
        String token = isAuthenticated ? jwtTokenUtil.generateToken(user) : null;

        return new JwtResponse(token, System.currentTimeMillis() + jwtTokenUtil.getJwtTokenValidity());
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }
}
