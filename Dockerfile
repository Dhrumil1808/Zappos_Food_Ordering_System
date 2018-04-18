FROM java:8
FROM maven:alpine
VOLUME /tmp
ARG JAR_FILE
COPY /target/zapjar.jar zappos.jar
ENTRYPOINT ["java","-jar","zappos.jar"]