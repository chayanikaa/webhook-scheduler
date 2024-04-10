# Use a Gradle image to build your application
FROM gradle:jdk11 as builder

# Copy your source code to the container
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Build your application
RUN gradle build --no-daemon

# Use OpenJDK for running the application
FROM openjdk:11-jre-slim

# Copy the built application from the builder stage
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/service.jar

WORKDIR /app

# Command to run your application
CMD ["java", "-jar", "service.jar"]
