# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 17, try this
FROM openjdk:latest

# Refer to Maven build -> finalName
ARG JAR_FILE=target/est-gateway.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/est-gateway.jar /opt/app/app-gateway.jar
COPY ${JAR_FILE} app-gateway.jar

# java -jar /opt/app/app-gateway.jar
ENTRYPOINT ["java","-jar","app-gateway.jar"]

## sudo docker run -p 8080:8080 -t docker-spring-boot:1.0
## sudo docker run -p 80:8080 -t docker-spring-boot:1.0
## sudo docker run -p 443:8443 -t docker-spring-boot:1.0