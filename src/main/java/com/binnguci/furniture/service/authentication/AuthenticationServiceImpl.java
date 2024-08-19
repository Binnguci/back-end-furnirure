package com.binnguci.furniture.service.authentication;

import com.binnguci.furniture.dto.request.AuthenticationRequest;
import com.binnguci.furniture.dto.request.LogoutRequest;
import com.binnguci.furniture.dto.response.JwtResponse;
import com.binnguci.furniture.entity.InvalidatedTokenEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.repository.InvalidatedTokenRepository;
import com.binnguci.furniture.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final InvalidatedTokenRepository invalidatedTokenRepository;

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

    @Override
    public void logout(LogoutRequest request) {
        log.info("Logging out user: {}", request.getToken());
        extractJwtIDAndSaveToken(request.getToken());
        SecurityContextHolder.clearContext();
    }

    private Authentication authenticate(String username, String password) {
        log.info("Authenticating user: {}", username);
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }
    private void extractJwtIDAndSaveToken(String token) {
        log.info("Extracting JWT ID from token: {}", token);
        String jwtID = jwtTokenUtil.extractJwtID(token);
        Date expired = jwtTokenUtil.extractExpiration(token);

        InvalidatedTokenEntity invalidatedToken = InvalidatedTokenEntity.builder()
                .tokenId(jwtID)
                .expired(expired)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }
}
