### Employ the builder pattern
FROM maven:3.6.1-jdk-8 as builder
### Provide Default Argument
WORKDIR /usr/src/app 
COPY ./target . 
## Use only a JRE to run application
FROM gcr.io/distroless/java:8
# FROM openjdk:7
## Copy Artifact from maven image
COPY --from=builder /usr/src/app/rss-account-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
