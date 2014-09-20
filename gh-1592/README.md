**Instructions:**

1. `mvn clean package` in both folders
1. copy `target/logback-boot.war` and `target/logback-vanilla.war` to `tomcat/webapps/`
1. start Tomcat and `tail -f tomcat/logs/catalina.out`
1. navigate to http://localhost:8080/logback-boot/ and http://localhost:8080/logback-vanilla/ and reload to trigger a single log event
1. edit `tomcat/webapps/logback-[boot|vanilla]/WEB-INF/classes/logback.xml` and change the TRY-TO-CHANGE-ME text to something else

**Result:** after several reloads logback-vanilla switches to the new log format, logback-boot never picks up the change.