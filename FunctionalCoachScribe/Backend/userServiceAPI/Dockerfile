FROM maven:3.9-sapmachine-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
FROM maven:3.6.3-jdk-11-slim
FROM tomcat:11.0.0-jdk17
COPY --from=build /home/app/target/userServiceAPI.war /usr/local/tomcat/webapps/ROOT.war

ENV SERVER_PORT=8082
ENV MONGO_HOST=CAPMongoDB_UserService
ENV MONGO_PORT=27017
ENV MONGO_DB=user_storage
ENV EUREKA_HOST=userService_API

#FROM maven:3.9.4-eclipse-temurin-17
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#FROM eclipse-temurin:17-jre
#COPY --from=build /home/app/target/WebcrawlerService.jar /WebcrawlerService.jar
#ENTRYPOINT ["java", "-jar", "/WebcrawlerService.jar"]