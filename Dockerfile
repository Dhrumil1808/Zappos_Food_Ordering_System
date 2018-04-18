FROM java:8
FROM maven:alpine
VOLUME /tmp
ARG JAR_FILE
COPY /target/zapjar.jar zappos.jar
RUN echo "Oh dang look at that ${JAR_FILE}"
ENTRYPOINT ["java","-jar","zappos.jar"]