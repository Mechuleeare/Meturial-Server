package com.meturial.global.security.jwt;

import com.meturial.domain.auth.domain.RefreshToken;
import com.meturial.domain.auth.domain.repository.RefreshTokenRepository;
import com.meturial.domain.auth.presentation.dto.response.TokenResponse;
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

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private final JwtProperty jwtProperty;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse getTokens(String email) {
        return new TokenResponse(
                generateAccessToken(email),
                LocalDateTime.now().plusSeconds(jwtProperty.getAccessExpiration()),
                generateRefreshToken(email)
        );
    }

    public String generateAccessToken(String email) {
        return generateToken(email, "access", jwtProperty.getAccessExpiration());
    }

    public String generateRefreshToken(String email) {
        String refreshToken = generateToken(email, "refresh", jwtProperty.getRefreshExpiration());
        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .token(refreshToken)
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
        String bearer = request.getHeader(HEADER);
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(PREFIX)) {
            return bearerToken.replace(PREFIX, "");
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
