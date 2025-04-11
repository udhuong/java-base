package com.udh.auth.infrastructure.security;

import com.udh.auth.domain.entity.AuthUser;
import com.udh.auth.domain.exception.AuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Tạo chuỗi access token
     */
    public String generateAccessToken(AuthUser user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("dev", 1);

        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(user.getId()))
                .claim("role", "") // có thể set role sau nếu cần
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Tạo chuỗi refresh token
     */
    public String generateRefreshToken(AuthUser user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpiration);

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .id(UUID.randomUUID().toString()) // Thêm jti (JWT ID) để quản lý blacklist
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Kiểm tra tính hợp lệ của token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            throw new AuthenticationException("JWT expired: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            throw new AuthenticationException("Unsupported JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            throw new AuthenticationException("Malformed JWT: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new AuthenticationException("Empty or null JWT: " + ex.getMessage());
        }
    }

    /**
     * Lấy payload từ token
     */
    public Claims getPayload(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Lấy userId từ token
     */
    public String getUserId(String token) {
        return getPayload(token).getSubject();
    }
}
