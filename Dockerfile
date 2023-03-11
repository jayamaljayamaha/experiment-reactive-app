FROM amazoncorretto:17-alpine3.16
COPY build/libs/experiment-reactive-app-0.0.1.jar experiment-reactive-app-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "experiment-reactive-app-0.0.1.jar"]