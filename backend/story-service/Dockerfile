FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/story-service
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/story-service/target/story-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8083
CMD java -jar story-service-0.0.1-SNAPSHOT.jar