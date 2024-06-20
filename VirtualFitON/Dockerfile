# First stage: Build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and the application code
COPY pom.xml .
COPY src ./src

# Clean and package the application
RUN mvn clean package -DskipTests

# Second stage: Create a minimal runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the first stage
COPY --from=build /app/target/VirtualFitON-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
