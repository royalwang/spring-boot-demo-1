# Spring Boot Log4j2 Demo

This module serves as a demo for a simple Spring Boot log4j2 application.
To avoid dependency conflicts, this module is not included in the parent pom file and cannot be run using IntelliJ. 
It uses `spring-boot-starter-parent` as parent pom. To run this module, please do the following:

```bash
cd log4j2
mvn clean package
java -jar target/log4j2-0.0.1-SNAPSHOT.jar
```

Then go to `localhost:8021` to trigger the logging. Finally, check `log` folder under `resources`.
