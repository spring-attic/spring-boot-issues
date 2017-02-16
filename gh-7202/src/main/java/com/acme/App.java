package com.acme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (Class<?> cls : App.class.getDeclaredClasses()) {
            if (cls.getSimpleName().startsWith("Pojo_")) {
                logger.warn("!!!" + cls.getSimpleName());
            }
        }
    }

//    public class Pojo_english_spring {} //ok
    public class Pojo_russian_весна {} //fail
}
