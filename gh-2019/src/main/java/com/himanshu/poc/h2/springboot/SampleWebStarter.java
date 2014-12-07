package com.himanshu.poc.h2.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebStarter {

	@Autowired
	DummyTblDao dummyTblDao;
	
	@Autowired
	PersonDao personDao;

	public static void main(String[] args) {
		SpringApplication.run(SampleWebStarter.class, args);
	}
	
}
