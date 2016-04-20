This is code to re-create issue [Spring Boot Gradle Plugin Includes Duplicate Jars #5749](https://github.com/spring-projects/spring-boot/issues/5749)

### Clean your maven 2 cache (Windows), for me it was at:
```
rmdir /s /q C:\Users\japoli\.m2\repository\com\sas\test\example
```

### Build project b, verify build.gradle: version '1.0.0-SNAPSHOT'
```
cd b
gradlew clean install
```

### Build project a, verify build.gradle: version '1.0.0-SNAPSHOT'
```
cd ..\a
gradlew clean install
```
    
### Build project b, change build.gradle: version '1.0.0'
```
cd ..\b
gradlew clean install
```

### Build project initial, after a build. unzip the gs-spring-boot-0.1.0.jar in the build\libs directory, you should see two b jars, b-1.0.0.jar and b-1.0.0-SNAPSHOT.jar.
```
cd ..\gs-spring-boot\initial
gradlew clean build
```