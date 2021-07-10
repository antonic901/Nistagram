FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/user-service
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/user-service/target/user-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8081
CMD java -jar user-service-0.0.1-SNAPSHOT.jar
