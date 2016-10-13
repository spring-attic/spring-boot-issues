package com.example;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Paths;

public class ResourceTests {

	@Test
	public void test() throws IOException {
		Resource resource = new ClassPathResource("/mydir");
		Paths.get(resource.getURI());
	}

}
