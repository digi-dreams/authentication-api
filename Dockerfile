FROM openjdk

WORKDIR /app

COPY target/authentication-api-0.0.1-SNAPSHOT.jar /app/authentication-api.jar

ENTRYPOINT ["java", "-jar", "authentication-api.jar"]