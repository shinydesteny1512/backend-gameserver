# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY api-spec /app/api-spec
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


## Use Temurin 21 JRE
#FROM eclipse-temurin:21-jre
#
## Set working directory
#WORKDIR /app
#
## Copy the JAR file dynamically
## This will copy the first JAR found in target/ (Maven) or build/libs/ (Gradle)
#COPY target/*.jar app.jar
#
## Expose default port
#EXPOSE 8080
#
## Run the JAR
#ENTRYPOINT ["java", "-jar", "app.jar"]
