# ---- Stage 1: Build the JAR ----
FROM maven:3.8.7-openjdk-17 AS builder

# Set working directory
WORKDIR /app

# Copy Maven dependencies and source code
COPY pom.xml .
COPY src ./src

# Build the application (JAR)
RUN mvn clean package -DskipTests

# ---- Stage 2: Create the runtime image ----
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=builder /app/target/historical-bitcoin-service.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
