package com.buurbak.api.security.config;

public class AuthenticationConfigConstants {
    // TODO Get from env variables
    public static final String SECRET = System.getenv("JWT_SECRET");

    // Note to self: don't get from env variables
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_IN_URL = "/auth/login";
    public static final String REFRESH_TOKEN_URL = "/auth/refresh";
}
