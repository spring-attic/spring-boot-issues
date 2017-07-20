package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.core.env.StandardEnvironment;

public class BindingTests {

	@Test
	public void bar() {
		StandardEnvironment environment = new StandardEnvironment();
		TestPropertyValues.of("app.bar.value=one").applyTo(environment);
		BindResult<Bar> bar = Binder.get(environment).bind("app.bar", Bar.class);
		assertThat(bar.get().getValue()).isNotNull();
	}

	@Test
	public void foos() {
		StandardEnvironment environment = new StandardEnvironment();
		TestPropertyValues.of("app.foo.value=one", "app.foo.foos.foo1.value=two")
				.applyTo(environment);
		BindResult<Foo> foo = Binder.get(environment).bind("app.foo", Foo.class);
		assertThat(foo.get().getValue()).isNotNull();
		assertThat(foo.get().getFoos().get("foo1")).isNotNull();
		assertThat(foo.get().getFoos().get("foo1")).isNotNull();
	}

}

class Bar {
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

class Foo {
	private String value;
	private Map<String, Foo> foos = new LinkedHashMap<>();

	public Map<String, Foo> getFoos() {
		return foos;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}