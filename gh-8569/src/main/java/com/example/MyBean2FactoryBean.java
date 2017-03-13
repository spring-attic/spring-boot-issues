package com.example;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class MyBean2FactoryBean extends AbstractFactoryBean<MyBean2> {

	private MyBean1 myBean1;

	public MyBean2FactoryBean(MyBean1 myBean1) {
		this.myBean1 = myBean1;
	}

	@Override
	protected MyBean2 createInstance() throws Exception {
		return new MyBean2(myBean1);
	}

	@Override
	public Class<?> getObjectType() {
		return MyBean2.class;
	}

}
