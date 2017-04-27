package com.example;

import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@ClassRule
	public static OutputCapture capture = new OutputCapture();

	@Test
	public void contextLoads() {
		capture.expect(containsString("Number:1"));
	}

}
