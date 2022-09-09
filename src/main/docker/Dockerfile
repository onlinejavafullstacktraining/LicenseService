FROM openjdk:20-slim
#RUN mkdir -p /usr/local/licensingservice
#ADD Licensing-service-0.0.1-SNAPSHOT.jar /usr/local/licensingservice/
#ADD run.sh run.sh
#RUN chmod +x run.sh
#CMD ./run.sh
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/local/licensingservice.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/local/licensingservice/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/local/licensingservice.jar
