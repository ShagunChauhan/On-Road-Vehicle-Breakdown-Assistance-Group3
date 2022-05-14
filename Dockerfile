FROM openjdk:11
LABEL maintainer="Shagun Chauhan"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","application.jar"]
ENV name breakdown-assistant