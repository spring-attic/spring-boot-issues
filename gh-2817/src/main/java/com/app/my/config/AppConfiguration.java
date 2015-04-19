package com.app.my.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties
@EnableWebMvc
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationProperties properties;

    // Serve up audio files directly from the external directory
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Serve audio
        registry.addResourceHandler("/audio/**").addResourceLocations(
                properties.getExternalDirectoryURI("audio/").toString()).setCachePeriod(300);

        // At least after the controller registry
        registry.setOrder(10);
    }

}
