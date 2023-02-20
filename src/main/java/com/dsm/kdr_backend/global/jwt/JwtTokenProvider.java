package com.dsm.kdr_backend.global.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.dsm.kdr_backend.global.jwt.exception.TokenUnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenTime;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenTime;
    
    private final UserDetailsService userDetailsService;

    public String generateAccessToken() {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .setIssuedAt(new Date())
                .setHeaderParam("typ", "access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken() {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTime * 1000))
                .setIssuedAt(new Date())
                .setHeaderParam("typ", "refresh")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        UserDetails details = userDetailsService.loadUserByUsername("");
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            return getBody(token).getExpiration().after(new Date());
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    public boolean isRefreshToken(String token) {
        try {
            return getHeader(token).get("typ").equals("refresh");
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

}
