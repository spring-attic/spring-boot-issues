## spring-boot:1.4.1.RELEASE
```
$ mvn && java -jar ./target/*.jar
...
java.io.FileNotFoundException: JAR entry com/acme/App$Pojo_russian_весна.class not found in /tmp/test/target/spring-boot-app-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes
    at org.springframework.boot.loader.jar.JarURLConnection.throwFileNotFound(JarURLConnection.java:180) ~[spring-boot-app-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
    at org.springframework.boot.loader.jar.JarURLConnection.connect(JarURLConnection.java:102) ~[spring-boot-app-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
    at org.springframework.boot.loader.jar.JarURLConnection.getInputStream(JarURLConnection.java:166) ~[spring-boot-app-0.0.1-SNAPSHOT.jar:0.0.1-SNAPSHOT]
    at org.springframework.core.io.UrlResource.getInputStream(UrlResource.java:169) ~[spring-core-4.3.3.RELEASE.jar!/:4.3.3.RELEASE]
    at org.springframework.core.type.classreading.SimpleMetadataReader.<init>(SimpleMetadataReader.java:50) ~[spring-core-4.3.3.RELEASE.jar!/:4.3.3.RELEASE]
    at org.springframework.core.type.classreading.SimpleMetadataReaderFactory.getMetadataReader(SimpleMetadataReaderFactory.java:98) ~[spring-core-4.3.3.RELEASE.jar!/:4.3.3.RELEASE]
    at org.springframework.core.type.classreading.CachingMetadataReaderFactory.getMetadataReader(CachingMetadataReaderFactory.java:102) ~[spring-core-4.3.3.RELEASE.jar!/:4.3.3.RELEASE]
    at org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider.findCandidateComponents(ClassPathScanningCandidateComponentProvider.java:279) ~[spring-context-4.3.3.RELEASE.jar!/:4.3.3.RELEASE]
    ... 26 common frames omitted
```
## spring-boot:1.3.8.RELEASE
```
$ mvn && java -jar ./target/*.jar
```
It works fine.