package org.springframework.boot.issues.gh6045;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Please change the @ComponentScan basePackages to reproduce the error!
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Configuration
// WORKING
@ComponentScan(basePackages = {
    "org.springframework.boot.issues.gh6045.server",
    "org.springframework.boot.issues.gh6045.service",
    "org.springframework.boot.issues.gh6045"})

// throws BeanCurrentlyInCreationException
//@ComponentScan(basePackages = {"org.springframework.boot.issues.gh6045",
//    "org.springframework.boot.issues.gh6045.server",
//    "org.springframework.boot.issues.gh6045.service"})

// throws BeanCurrentlyInCreationException
//@ComponentScan(basePackages = "org.springframework.boot.issues.gh6045")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication(Application.class);
        springApp.run(args);
    }

}
