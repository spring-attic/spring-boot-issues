package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

public class TestPropertySourceLocator implements PropertySourceLocator {

	@Override
	public PropertySource<?> locate(Environment environment) {
		Map<String, Object> source = new HashMap<>();
		source.put("logging.pattern.level", "baz");
		return new MapPropertySource("test", source);
	}

}
