package com.example.executorissue.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@TestConfiguration
public class ExecutorConfig {

    @Bean
    Executor myCustomTaskExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}
