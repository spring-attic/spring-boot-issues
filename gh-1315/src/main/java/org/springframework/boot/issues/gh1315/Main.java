package org.springframework.boot.issues.gh1315;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args).getBean(Test.class);
	}

	@Component
	public static class TestFactory implements FactoryBean<Test> {

		private ResourcePatternResolver resolver;

		@Autowired
		public TestFactory(ResourcePatternResolver resolver) {
			this.resolver = resolver;
		}

		@Override
		public Test getObject() throws Exception {
			return new Test();
		}

		@Override
		public Class<Test> getObjectType() {
			return Test.class;
		}

		@Override
		public boolean isSingleton() {
			return true;
		}
	}

	public static class Test {

	}
}