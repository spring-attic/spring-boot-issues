package com.example;

import javax.sql.DataSource;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Gh2397Application {

    public static void main(String[] args) {
        SpringApplication.run(Gh2397Application.class);
    }
    
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {
			
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
					Tomcat tomcat) {
				tomcat.enableNaming();
				TomcatEmbeddedServletContainer container = super.getTomcatEmbeddedServletContainer(tomcat);
				for (Container child: container.getTomcat().getHost().findChildren()) {
					if (child instanceof Context) {
						ClassLoader contextClassLoader = ((Context)child).getLoader().getClassLoader();
						Thread.currentThread().setContextClassLoader(contextClassLoader);
						break;
					}
				}
				return container;
			}

			@Override
			protected void postProcessContext(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/myDataSource");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", "org.h2.Driver");
				resource.setProperty("url", "jdbc:h2:example");

				context.getNamingResources().addResource(resource);
			}
		};
	}
}
