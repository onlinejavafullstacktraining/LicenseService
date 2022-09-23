FROM openjdk:20-slim as build
ARG JAR_FILE
COPY target/${JAR_FILE} license.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/license.jar"]
#ADD run.sh run.sh
#RUN chmod +x run.sh
#CMD ./run.sh

##stage 1
##Start with a base image containing Java runtime
#FROM openjdk:11-slim as build
#
## Add Maintainer Info
#LABEL maintainer="Illary Huaylupo <illaryhs@gmail.com>"
#
## The application's jar file
#ARG JAR_FILE
#
## Add the application's jar to the container
#COPY target/${JAR_FILE} app.jar
#
##unpackage jar file
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)
#
##stage 2
##Same Java runtime
#FROM openjdk:11-slim
#
##Add volume pointing to /tmp
#VOLUME /tmp
#
##Copy unpackaged application to new container
#ARG DEPENDENCY=/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
##execute the application
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.licenseservice.LicenseServiceApplication"]