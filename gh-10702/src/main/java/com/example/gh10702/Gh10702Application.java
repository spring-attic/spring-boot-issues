package com.example.gh10702;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Gh10702Application {

	public static void main(String[] args) {
		SpringApplication.run(Gh10702Application.class, args);
	}

	@RestController
	static class MyController {
		@GetMapping("/")
		String home() {
			return "hello!!";
		}

	}

}
