FROM openjdk:8-jdk-alpine
COPY dob-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]