FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/post-service
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/post-service/target/post-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8082
CMD java -jar post-service-0.0.1-SNAPSHOT.jar