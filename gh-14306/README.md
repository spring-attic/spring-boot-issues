# [gh-14306](https://github.com/spring-projects/spring-boot/issues/14306) Sample Application

Steps to reproduce the issue:

1. At a command line, run `./gradlew bootRunWar`, which simply runs the executable WAR using `java -jar`. (The issue is only reproducible when the application runs from a WAR.)
2. Open `testUrls.http` and issue the GET requests it lists. (In IntelliJ IDEA, you can simply click the Run icon in the gutter next to each GET line.)
3. As noted in `testUrls.http`, a request for a WAR-hosted resource that includes the Range header will fail with an HTTP 416.
