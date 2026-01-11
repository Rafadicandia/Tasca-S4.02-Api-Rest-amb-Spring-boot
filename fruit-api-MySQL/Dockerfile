# STAGE 1: Build process
# Use an official Maven image with JDK 21 to compile the source code.
FROM maven:3.9.6-eclipse-temurin-21 AS build

  # Set the working directory inside the container.
WORKDIR /app

  # Copy the pom.xml first to download dependencies.
  # This leverages Docker's layer caching to speed up subsequent builds.
COPY pom.xml .
RUN mvn dependency:go-offline

  # Copy the source code and build the application.
  # Tests are skipped to optimize build time since they were already run during CI/TDD.
COPY src ./src
RUN mvn clean package -DskipTests

  # STAGE 2: Runtime environment
  # Use a lightweight JRE image for the final production environment.
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

  # Copy only the compiled .jar file from the build stage.
  # This multi-stage approach significantly reduces the final image size.
COPY --from=build /app/target/*.jar app.jar

  # Expose the port the application runs on.
EXPOSE 8080

  # Define the entry point to run the application.
ENTRYPOINT ["java", "-jar", "app.jar"]