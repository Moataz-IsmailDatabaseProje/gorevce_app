# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR
COPY target/freelancer-service-*.jar app.jar

# Expose the server port
EXPOSE 8083

# Default command to run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]