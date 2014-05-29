package com.espn.api.motorsports.initialize;

import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.ServletContextAware;
//import com.espn.api.motorsports.config.ConnectionSettings;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableAutoConfiguration
@ComponentScan({"com.espn.api"})
//@EnableConfigurationProperties(ConnectionSettings.class)

public class WebAppInitializer extends SpringBootServletInitializer implements ServletContextAware  {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebAppInitializer.class, args);
		while (true) {
			Thread.sleep(60000);
		}
	}

	public WebAppInitializer() {
		super();
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(WebAppInitializer.class);
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// Spring/Jersey seems to have an issue with Spring 4 when running as WAR
		// this helps to avoid the Jersey startup listener from killing Spring
		servletContext.setInitParameter("contextConfigLocation", "true");
	}

	
}
