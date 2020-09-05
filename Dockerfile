FROM openjdk:8-jdk-alpine

MAINTAINER hiro9134@gmail.com

COPY target/xpmanagement-0.0.1-SNAPSHOT.jar /opt/app/app.jar

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]