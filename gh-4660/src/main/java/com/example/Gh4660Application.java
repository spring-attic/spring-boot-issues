package com.example;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
public class Gh4660Application {

	public static void main(String[] args) {
		SpringApplication.run(Gh4660Application.class, args);
	}

    @RequestMapping(value="/optionalRequestPart", method=RequestMethod.POST)
	public void optionalRequestPart(@RequestPart("file") Optional<MultipartFile> file) {
    	file.ifPresent( System.out::println );
	}
}
