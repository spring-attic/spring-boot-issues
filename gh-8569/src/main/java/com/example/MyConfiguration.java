package com.example;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(MyConfigurationProperties.class)
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyConfiguration {

//	everything works if properties are not passed to bean method
//	@Bean
//	public MyBean1 myBean1() {
//		return new MyBean1();
//	}

	@Bean
	public MyBean1 myBean1(MyConfigurationProperties myConfigurationProperties) {
		return new MyBean1();
	}

}
