package com.example;

public class MyLinkedRes {

	String aField;

	public MyLinkedRes(String aField) {
		super();
		this.aField = aField;
	}

	public String getaField() {
		return aField;
	}

	public void setaField(String aField) {
		this.aField = aField;
	}

	@Override
	public String toString() {
		return "MyLinkedRel [aField=" + aField + "]";
	}
}