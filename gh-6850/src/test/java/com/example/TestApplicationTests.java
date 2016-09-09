package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Tracer tracer;

	@Autowired
	private TestRestTemplate template;
	
	@Before
	public void init() {
		tracer.reset();
	}

	@Test
	public void sunnyDay() {
		template.getForObject("http://localhost:" + port + "/", String.class);
		System.err.println("OK: " + tracer.getTraces());
	}

	@Test
	public void errorPage() {
		template.getForObject("http://localhost:" + port + "/notthere", String.class);
		System.err.println("Not OK: " + tracer.getTraces());
	}

}
