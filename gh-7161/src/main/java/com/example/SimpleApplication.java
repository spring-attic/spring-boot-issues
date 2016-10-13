package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Paths;

@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args) throws IOException {
		Resource resource = new ClassPathResource("/mydir");
		Paths.get(resource.getURI());
		SpringApplication.run(SimpleApplication.class, args);
	}
}
