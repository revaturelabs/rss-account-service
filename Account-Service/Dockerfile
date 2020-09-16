### Employ the builder pattern
FROM maven:3.6.1-jdk-8 as builder

### Provide Default Argument
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package

## Use only a JRE to run application
FROM gcr.io/distroless/java:8
## Copy Artifact from maven image
COPY --from=builder /usr/src/app/target/H2Demo-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app
CMD ["app.jar"]