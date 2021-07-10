FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/admin-service
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/admin-service/target/admin-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8085
CMD java -jar admin-service-0.0.1-SNAPSHOT.jar