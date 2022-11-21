package com.buurbak.api.security.util;

import com.auth0.jwt.algorithms.Algorithm;

public class SecurityUtils {
    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(System.getenv("JWT_SECRET"));
    }
}
