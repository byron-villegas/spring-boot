# Java 8
FROM openjdk:8-jdk-alpine

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Refer to Maven build -> finalName
ARG JAR_FILE=target/spring-boot.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/springboot.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
