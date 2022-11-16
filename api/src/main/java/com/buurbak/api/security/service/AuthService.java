package com.buurbak.api.security.service;

import com.buurbak.api.security.config.TokenProvider;
import com.buurbak.api.security.dto.LoginUserDTO;
import com.buurbak.api.security.dto.TokenDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenDTO refreshTokens(Authentication authentication) {
        return new TokenDTO(
                jwtTokenUtil.generateAccessToken(authentication),
                jwtTokenUtil.generateRefreshToken(authentication)
        );
    }

    public TokenDTO generateTokens(LoginUserDTO loginUserDTO) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getUsername(),
                        loginUserDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new TokenDTO(
                jwtTokenUtil.generateAccessToken(authentication),
                jwtTokenUtil.generateRefreshToken(authentication)
        );
    }
}
