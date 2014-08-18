### Reproducing the problem

Build the project and then run the resulting jar file, supplying Spring Instrument as a Java agent:

```
java -javaagent:/path/to/spring-instrument-4.0.4.RELEASE.jar -jar target/gh-863-1.0.0-SNAPSHOT.jar
```

The output will indicate that instrumentation is not available:

```
Instrumentation is available: false
```