package com.example;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.example.MyConfiguration.MyBean1;

@Configuration
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(MyBean2FactoryBean.class);
		bdb.addConstructorArgReference("myBean1");
		registry.registerBeanDefinition("myBean2", bdb.getBeanDefinition());
	}

	public static class MyBean2FactoryBean extends AbstractFactoryBean<MyBean2> {

		MyBean1 myBean1;

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

	public static class MyBean2 {

		MyBean1 myBean1;

		public MyBean2(MyBean1 myBean1) {
			this.myBean1 = myBean1;
		}
	}

}
