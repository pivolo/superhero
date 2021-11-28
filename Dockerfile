FROM openjdk:11-jre-slim

WORKDIR /app
COPY ./target/superhero-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "superhero-0.0.1-SNAPSHOT.jar"]