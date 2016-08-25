package com.example;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Gh6688Application {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void postConstruct() {
		logger.debug("************** Debug log message *****************");
	}

	public static void main(String[] args) {
		SpringApplication.run(Gh6688Application.class, args);
	}
}
