package com.meturial.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    private final String secretKey;

    @Value("${jwt.access_exp}")
    private final Long accessExpiration;

    @Value("${jwt.refresh_exp}")
    private final Long refreshExpiration;
}
