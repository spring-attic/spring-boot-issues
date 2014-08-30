package org.springframework.boot.issues.gh1370;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    	@Override
    	public void init(AuthenticationManagerBuilder auth) throws Exception {
    		auth.inMemoryAuthentication()
    		.withUser("sample").password("secret").roles("USER");
    	}
    	
    }
    
    @Configuration
    @EnableWebSecurity
    static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    }
}
