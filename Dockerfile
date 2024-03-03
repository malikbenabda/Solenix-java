# Stage 1: Build the application
FROM adoptopenjdk:17-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image
FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

# Build the Docker image on bash
## docker build -t spacecraft-events-app .

# Run the Docker container:
##  docker run -d -p 8080:8080 spacecraft-events-app