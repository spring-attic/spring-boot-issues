## Sample application
Example of Spring Boot 1.3.5 app failing to load <springProperty> from a custom.properties file set via a @PropertySource 
in the SpringBootApplication. When the property is set in application.properties, it works as expected.
The reason for this is that when  org.springframework.boot.logging.logback.SpringPropertyAction.getValue is called the environment does not contain all
property sources in propertySourceList

To Reproduce
1. Execute spring-boot:run and folder_IS_UNDEFIDED is created because custom.log-dir=logfolder was not loaded from custom.properties.
2. Uncomment the line application.properties and the application runs as expected.

Issue can be found here: https://github.com/spring-projects/spring-boot/issues/6219
