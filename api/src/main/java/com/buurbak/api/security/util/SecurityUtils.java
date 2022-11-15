package com.buurbak.api.security.util;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SecurityUtils {
    @Value("${jwt-secret}")
    private String JWT_SECRET;

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET.getBytes());
    }
}
