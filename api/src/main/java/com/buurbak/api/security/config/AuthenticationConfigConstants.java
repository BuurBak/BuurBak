package com.buurbak.api.security.config;

public class AuthenticationConfigConstants {
    // TODO Get from env variables
    public static final String SECRET = "SECRET";
    public static final long EXPIRATION_TIME = 864000000; // 10 days

    // Note to self: don't get from env variables
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/registration";
}
