package de.malkusch.quotedcookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SSCCE {

	public static void main(String[] args) {
		SpringApplication.run(SSCCE.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	public String printCookie(@CookieValue("foo") String foo) {
		return foo;
	}

}
