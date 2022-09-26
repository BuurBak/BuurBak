package com.buurbak.api.security.config;

public class AuthenticationConfigConstants {
    // TODO Get from env variables
    public static final String SECRET = "SECRET";
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 864000000; // 10 days
    public static final long REFRESH_TOKEN_EXPIRATION_TIME_IN_DAYS = 365; // 1 year

    // Note to self: don't get from env variables
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/register";
    public static final String SIGN_IN_URL = "/auth/login";
    public static final String ISSUER = "BuurBak";
}
