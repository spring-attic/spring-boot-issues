package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
// @EnableWebSecurity
public class DemoApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/main.css");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/secure").hasRole("ADMIN")
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin()
				.permitAll()
				.defaultSuccessUrl("/", true)
			.and()
				.logout().logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("tom").password("tom").roles("ADMIN", "USER")
				.and()
				.withUser("jack").password("jack").roles("USER");
	}

	@Controller
	public static class HomeController {

		@GetMapping
		public String index() {
			return "index";
		}

		@GetMapping("/secure")
		@ResponseBody
		public String secure() {
			return "Secure";
		}

	}

}
