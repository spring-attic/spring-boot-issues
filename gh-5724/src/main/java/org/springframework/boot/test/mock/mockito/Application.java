package org.springframework.boot.test.mock.mockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public BeanA beanA(BeanB beanB) {
        return new BeanA(beanB);
    }

    @RefreshScope
    @Bean
    public BeanB beanB() {
        return new BeanB();
    }
}