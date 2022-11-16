package com.buurbak.api.security.service;

import com.auth0.jwt.JWT;
import com.buurbak.api.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TokenService {
    private final SecurityUtils securityUtils;

    public static final int ACCESS_TOKEN_DURATION = 8 * 60 * 60 * 1000;
    public static final int REFRESH_TOKEN_DURATION = ACCESS_TOKEN_DURATION * 3 * 6;

    public String generateAccessToken(HttpServletRequest request, String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_DURATION))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", roles)
                .sign(securityUtils.getAlgorithm());
    }

    public String generateRefreshToken(HttpServletRequest request, String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_DURATION))
                .withIssuer(request.getRequestURL().toString())
                .sign(securityUtils.getAlgorithm());
    }
}
