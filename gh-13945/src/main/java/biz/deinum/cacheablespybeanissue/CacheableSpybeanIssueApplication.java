package biz.deinum.cacheablespybeanissue;

import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class CacheableSpybeanIssueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheableSpybeanIssueApplication.class, args);
    }

    @Bean
    public ApplicationRunner orders(OrderService os) {
        return args -> {

            PartialKey key = new PartialKey("42");
            String id = UUID.randomUUID().toString();
            System.out.println(os.find(id, key));
            System.out.println(os.find(id, key));
            System.out.println(os.find(id, key));
        };
    }
}
