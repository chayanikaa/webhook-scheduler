# Use a Gradle image with JDK 17 for building the application
FROM gradle:7.5.1-jdk17 as builder

# Copy your source code to the container
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Build your application
RUN gradle build --no-daemon

# Use OpenJDK 17 for running the application
FROM openjdk:17-slim

# Copy the built application from the builder stage
COPY --from=builder /home/gradle/src/build/libs/TimerCreatorService-0.0.1-SNAPSHOT.jar /app/service.jar

WORKDIR /app

# Command to run your application
CMD ["java", "-jar", "service.jar"]
