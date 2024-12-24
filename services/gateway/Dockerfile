# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR
COPY target/gateway-*.jar app.jar

# Expose the server port
EXPOSE 8080

# Default command to run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]