package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author Stephane Nicoll
 */
@ConfigurationProperties("foo")
@Validated
public class SampleProperties {

	@NotNull(message = "That should not be null")
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
