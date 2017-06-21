package org.foo.devtools.demo;

import org.bar.devtools.foo.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


/**
 * @author zaunerm
 */
@EnableCaching
@SpringBootApplication
public class App {
    @Autowired
    private BarService barService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).run(args);
    }

    @Bean
    String bar() {
        System.out.println(barService.bar("foo"));
        return barService.bar("foo");
    }
}
