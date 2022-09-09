FROM openjdk:20-slim
#RUN mkdir -p /usr/local/licensingservice
ARG JAR_FILE
COPY target/${JAR_FILE} license.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/license.jar"]


