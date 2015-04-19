package com.app.my.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableConfigurationProperties
public class ThreadConfiguration extends WebMvcConfigurerAdapter{

    
    private ThreadPoolTaskExecutor executor;
    
    @Bean
    AsyncTaskExecutor asyncTaskExecutor() {
        return getThreadPool();
    }
    
    @Bean
    AsyncListenableTaskExecutor asyncListenableTaskExecutor() {
        return getThreadPool();
    }
    
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(executor);
    }
    
    
    private ThreadPoolTaskExecutor getThreadPool() {
        if (executor == null) {
            executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(100);
            executor.setQueueCapacity(10);
        }
        return executor;
    }

}
