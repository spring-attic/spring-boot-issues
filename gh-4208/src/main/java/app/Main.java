package app;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@Configuration
@ComponentScan("app")
public class Main {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Main.class)
                .web(false)
                .run(args);
    }
}