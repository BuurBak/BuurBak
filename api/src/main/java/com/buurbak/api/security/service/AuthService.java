package com.buurbak.api.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.buurbak.api.security.dto.TokenDTO;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static com.buurbak.api.security.config.AuthenticationConfigConstants.SECRET;
import static com.buurbak.api.security.config.AuthenticationConfigConstants.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserService appUserService;
    private final TokenService tokenService;

    public TokenDTO refreshAccessAndRefreshTokens(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                String refreshToken = authorizationHeader.substring(TOKEN_PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedjwt = verifier.verify(refreshToken);
                String username = decodedjwt.getSubject();

                AppUser appUser = appUserService.findByUsername(username);
                String accessToken = tokenService.generateAccessToken(request, username, appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

                return new TokenDTO(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Refresh token is missing from Authorization header");
        }
    }
}
