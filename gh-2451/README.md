Steps to reproduce the problem:

1. ./gradlew clean build
2. java -jar app/build/libs/app.jar
3. curl localhost:8080/nested.txt

The problem goes away upon removing the dependency on
`org.apache.tomcat.embed:tomcat-embed-jasper`.