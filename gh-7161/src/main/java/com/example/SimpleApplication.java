package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@SpringBootApplication
@EnableConfigServer
public class SimpleApplication {

	public static void main(String[] args) throws IOException {
		Resource resource = new ClassPathResource("/mydir");
		// Paths.get(resource.getURI());
		URI uri = resource.getURI();
		System.out.println(uri);
		FileSystems.newFileSystem(uri, Collections.emptyMap());
		Path path = Paths.get(uri);
		SpringApplication.run(SimpleApplication.class, args);
	}
}
