FROM openjdk:8-jdk-alpine
MAINTAINER Andrea Fiore
COPY target/blogpost-1.0-SNAPSHOT.jar blogpost.jar
ENTRYPOINT ["java","-jar","/blogpost.jar"]
