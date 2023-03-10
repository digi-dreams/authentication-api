FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/authentication-api-0.0.1-SNAPSHOT.jar authentication-api.jar

EXPOSE ${PORT}
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar", "authentication-api.jar"]