package org.springframework.boot.issues;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletContextInitializer startupListener(MessageSource messageSource) {

        return servletContext -> {
            System.out.println("\n\n\n=======================================================");
            System.out.println(messageSource.getMessage("sample.message", new Object[]{}, Locale.ENGLISH));
            System.out.println("=======================================================\n\n\n");
        };

    }

}
