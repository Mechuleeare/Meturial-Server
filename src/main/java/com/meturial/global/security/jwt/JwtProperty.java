package com.meturial.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    private final String header;
    private final String prefix;
    private final String  secretKey;
    private final Long accessExpiration;
    private final Long refreshExpiration;
}
