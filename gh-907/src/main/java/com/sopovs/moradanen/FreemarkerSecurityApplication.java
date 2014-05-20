package com.sopovs.moradanen;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author isopov
 * @since 20.05.2014
 */
@EnableAutoConfiguration
@Controller
public class FreemarkerSecurityApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(FreemarkerSecurityApplication.class)
				.properties("security.user.password=password")
				.run();
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
