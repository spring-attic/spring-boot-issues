Working case: check out current version and run mvn spring-boot:run
Go to http://localhost:8080/test, works fine
Delete src/main/webapp/WEB-INF/classes/META-INF/services/javax.el.ExpressionFactory and rerun, error is thrown:
2014-06-28 01:46:28,640 [http-nio-8080-exec-1] ERROR org.apache.catalina.core.ContainerBase.[Tomcat].[localhost].[/].[jsp] - Servlet.service() for servlet jsp threw exception
java.lang.ClassNotFoundException: com.sun.el.ExpressionFactoryImpl
at org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedWebappClassLoader.loadClass(TomcatEmbeddedWebappClassLoader.java:75)
at org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1571)
at javax.el.FactoryFinder.newInstance(FactoryFinder.java:87)
at javax.el.FactoryFinder.find(FactoryFinder.java:197)
at javax.el.ExpressionFactory.newInstance(ExpressionFactory.java:197)
at javax.el.ExpressionFactory.newInstance(ExpressionFactory.java:168)
at org.apache.jasper.compiler.PageInfo.<init>(PageInfo.java:79)
