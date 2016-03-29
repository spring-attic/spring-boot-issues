package com.example;

import org.springframework.web.client.RestTemplate;

public final class BasicAuthRestTemplate extends RestTemplate {
    private String username;
    private String password;

    public BasicAuthRestTemplate(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    // adding interceptors etc..
}
