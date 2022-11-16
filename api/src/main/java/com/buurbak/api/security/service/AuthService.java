package com.buurbak.api.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.buurbak.api.security.dto.TokenDTO;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserService appUserService;
    private final TokenService tokenService;

    @Value("${jwt-secret}")
    private String JWT_SECRET;

    public TokenDTO refreshAccessAndRefreshTokens(HttpServletRequest request) throws IllegalStateException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedjwt = verifier.verify(refreshToken);
                String username = decodedjwt.getSubject();

                AppUser appUser = appUserService.findByEmail(username);
                String accessToken = tokenService.generateAccessToken(request, username, appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

                return new TokenDTO(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Refresh token is missing from Authorization header");
        }
    }
}
