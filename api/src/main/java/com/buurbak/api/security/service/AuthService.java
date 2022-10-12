package com.buurbak.api.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.buurbak.api.security.dto.TokenDTO;
import com.buurbak.api.security.model.Role;
import com.buurbak.api.security.model.User;
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
    private final UserService userService;
    private final TokenService tokenService;

    public TokenDTO refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
                String refresh_token = authorizationHeader.substring(TOKEN_PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedjwt = verifier.verify(refresh_token);
                String username = decodedjwt.getSubject();

                User user = userService.findByUsername(username);
                String access_token = tokenService.generateAccessToken(request, username, user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

                return new TokenDTO(access_token, refresh_token);
        } else {
            throw new RuntimeException("refresh token is missing");
        }
    }
}
