package com.buurbak.api.security.util;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SecurityUtils {
    private final String JWT_SECRET;

    @Autowired
    public SecurityUtils(@Value("${jwt.secret}") String JWT_SECRET) {
        this.JWT_SECRET = JWT_SECRET;
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET.getBytes());
    }
}
