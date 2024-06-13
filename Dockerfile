# OpenJDK 11 is used as the base image
FROM openjdk:11-jre-slim

# We copy the application jar file to the container
COPY target/your-app-name.jar /app.jar

# Command to be executed when the container is run
ENTRYPOINT ["java", "-jar", "/app.jar"]
