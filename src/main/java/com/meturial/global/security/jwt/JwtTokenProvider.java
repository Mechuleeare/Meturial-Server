package com.meturial.global.security.jwt;

import com.meturial.domain.auth.domain.RefreshToken;
import com.meturial.domain.auth.domain.repository.RefreshTokenRepository;
import com.meturial.global.exception.ExpiredJwtException;
import com.meturial.global.exception.InvalidJwtException;
import com.meturial.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperty jwtProperty;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String email) {
        return generateToken(email, "access", jwtProperty.getAccessExpiration());
    }

    public String generateRefreshToken(String email) {
        String refreshToken = generateToken(email, "refresh", jwtProperty.getRefreshExpiration());
        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .ttl(jwtProperty.getRefreshExpiration())
                .build());

        return refreshToken;
    }

    private String generateToken(String email, String type, Long exp) {
        Date now = new Date();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecretKey())
                .setSubject(email)
                .claim("type", type)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperty.getHeader());
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperty.getPrefix())) {
            return bearerToken.replace(jwtProperty.getPrefix(), "");
        }
        return null;
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }
}