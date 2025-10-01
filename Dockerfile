# Use Temurin 21 JRE
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the JAR file dynamically
# This will copy the first JAR found in target/ (Maven) or build/libs/ (Gradle)
COPY target/*.jar app.jar

# Expose default port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
