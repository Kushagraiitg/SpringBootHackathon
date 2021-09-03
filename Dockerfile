FROM openjdk:11-jre

COPY target/SpringAPI-0.0.1-SNAPSHOT.jar app.jar
COPY symbols.csv /symbols.csv
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
