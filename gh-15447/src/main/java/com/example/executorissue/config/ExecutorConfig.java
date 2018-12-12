package com.example.executorissue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean
    Executor taskExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
