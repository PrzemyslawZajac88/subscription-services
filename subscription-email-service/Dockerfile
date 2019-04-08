FROM openjdk:13-ea-14-jdk-alpine3.9
RUN apk add postfix
COPY target/app.jar app.jar
ENTRYPOINT postfix start && java -jar app.jar


