FROM maven:3.9-sapmachine-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:11.0.0-jdk17
COPY --from=build /home/app/target/ChatService.war /usr/local/tomcat/webapps/ROOT.war
#COPY --from=build ROOT.war /usr/local/tomcat/webapps/
#COPY --from=build /path/to/generated/war/chatserice.war /usr/local/tomcat/webapps/ROOT.war


ENV SERVER_PORT=8084
ENV MONGO_HOST=CapstoneUserTest
ENV MONGO_PORT=27019
ENV MONGO_DB=UserCapstone
