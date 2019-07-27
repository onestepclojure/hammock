FROM openjdk:8-alpine

COPY target/uberjar/hammock.jar /hammock/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/hammock/app.jar"]
