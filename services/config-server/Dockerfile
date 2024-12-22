# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR
COPY target/config-server-*.jar app.jar

# Copy the configurations folder
COPY ./src/main/resources/configurations/ /configurations/

# Expose the server port
EXPOSE 8888

# Default command to run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
