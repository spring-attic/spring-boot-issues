package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Repro1620Application {

	// Uncomment this show now JSR310 module
	// @Bean
	// public HttpMessageConverters messageConverters() {
	// return new HttpMessageConverters();
	// }

	public static void main(String[] args) {
		SpringApplication.run(Repro1620Application.class, args);
	}

}
