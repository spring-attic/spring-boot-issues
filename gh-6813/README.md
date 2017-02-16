Starting this application takes forever, as something in
spring-boot-starter-actuator tries to resolve  hostnames
of all URL beans (see strace).

The application will start if either not spring-boot-starter-actuator
is on the class path, the List<URL> is not a managed bean or
using spring-boot-1.3.7.
