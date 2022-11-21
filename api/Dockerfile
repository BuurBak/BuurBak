# Use the official maven/Java 17 Temurin image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.8.6-eclipse-temurin-17 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use Temurin JDK for base image.
# https://hub.docker.com/_/eclipse-temurin/
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 80
COPY --from=builder /app/target/*.jar /app/*.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/*.jar"]
