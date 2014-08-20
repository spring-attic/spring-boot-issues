package base;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyApp {

    @Value("${SOME_ARG:}")
    private String someArg;

    @Bean
    public String dummy() {
        Assert.state(someArg.equals("someValue"), "SOME_ARG is not set");
        return "dummy";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApp.class, args);
    }


}
