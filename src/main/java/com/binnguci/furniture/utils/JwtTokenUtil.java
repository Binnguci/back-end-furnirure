package com.binnguci.furniture.utils;

import com.binnguci.furniture.repository.InvalidatedTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil implements Serializable {
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final InvalidatedTokenRepository tokenRepository;

    public String generateToken(UserDetails userDetails) {
        log.info("Generating token for user: {}", userDetails.getUsername());
        if (userDetails == null) {
            return null;
        }
        Map<String, Object> claims = extractUserRoles(userDetails);
        return createToken(claims, userDetails.getUsername());
    }

    private Map<String, Object> extractUserRoles(UserDetails userDetails) {
        log.info("Extracting user roles for user: {}", userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        if (userDetails != null) {
            claims.put("isAdmin", userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN")));
            claims.put("isUser", userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("USER")));
        }
        return claims;
    }

    private String createToken(Map<String, Object> claims, String username) {
        log.info("Creating token for user: {}", username);
        claims.put("jwtID", UUID.randomUUID().toString());
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        log.info("Getting signing key");
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        log.info("Extracting all claims from token");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
        log.info("Extracting claims from token");
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    public String extractJwtID(String token) {
        log.info("Extracting jwtID from token");
        return extractClaims(token, claims -> claims.get("jwtID", String.class));
    }

    public String extractUsername(String token) {
        log.info("Extracting username from token");
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        log.info("Extracting expiration from token");
        return extractClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        log.info("Checking if token is expired");
        return extractExpiration(token).before(new Date());
    }

    private boolean isExists(String token) {
        log.info("Checking if token exists in database");
        return tokenRepository.existsById(extractJwtID(token));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        log.info("Validating token");
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isExists(token);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token) && !isExists(token);
    }

    public long getJwtTokenValidity() {
        return JWT_TOKEN_VALIDITY;
    }
}
