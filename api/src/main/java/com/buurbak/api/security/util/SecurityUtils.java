package com.buurbak.api.security.util;

import com.auth0.jwt.algorithms.Algorithm;

import static com.buurbak.api.security.config.AuthenticationConfigConstants.SECRET;

public class SecurityUtils {
    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET);
    }
}
