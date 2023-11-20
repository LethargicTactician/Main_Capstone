FROM maven:3.9-sapmachine-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
FROM maven:3.6.3-jdk-11-slim
FROM tomcat:11.0.0-jdk17
COPY --from=build /home/app/target/UserServiceAPI.war /usr/local/tomcat/webapps/ROOT.war

ENV SERVER_PORT=8081
ENV MONGO_HOST=CapstoneUserTest
ENV MONGO_PORT=27019
ENV MONGO_DB=UserCapstone
# ENV EUREKA_HOST=CAPEurekaRegistry

#FROM maven:3.9.4-eclipse-temurin-17
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

#FROM eclipse-temurin:17-jre
#COPY --from=build /home/app/target/WebcrawlerService.jar /WebcrawlerService.jar
#ENTRYPOINT ["java", "-jar", "/WebcrawlerService.jar"]