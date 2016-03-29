package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        // SSL cert. termination takes place here..

        return new BasicAuthRestTemplate(
            "test",
            "test"
        );
    }
}
