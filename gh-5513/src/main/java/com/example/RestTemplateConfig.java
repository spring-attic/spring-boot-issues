package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestTemplateConfig {
    @Autowired
    private MappingJackson2HttpMessageConverter jsonConverter;

    @Autowired
    private MappingJackson2XmlHttpMessageConverter xmlConverter;

    @Bean
    public RestTemplate restTemplate() {
        // SSL cert. termination takes place here..

        RestTemplate client = new BasicAuthRestTemplate(
            "test",
            "test"
        );

        client.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(
            jsonConverter,
            xmlConverter
        ));

        return client;
    }
}
