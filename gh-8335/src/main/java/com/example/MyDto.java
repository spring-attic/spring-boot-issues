package com.example;

import org.springframework.hateoas.ResourceSupport;

public class MyDto extends ResourceSupport {

	private String field1;


	public MyDto(String field1) {
		super();
		this.field1 = field1;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	@Override
	public String toString() {
		return "MyDco [field1=" + field1 + "]";
	}
}