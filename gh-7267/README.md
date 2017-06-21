Project showcasing error [gh-7267](https://github.com/spring-projects/spring-boot/issues/7267)

If you run this with `foo:bootRun`, it will fail to start due to:
i.e.: `./gradlew foo:bootRun` 

> Caused by: java.lang.IllegalAccessError: class org.bar.devtools.autoconfig.demo.BarServiceImpl$$EnhancerBySpringCGLIB$$4fd4daa1 cannot access its superclass org.bar.devtools.autoconfig.demo.BarServiceImpl

If you remove devtools from foo's dependencies, it runs fine.