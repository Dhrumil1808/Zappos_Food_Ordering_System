FROM java:8
FROM maven:alpine
VOLUME /tmp
WORKDIR /app
COPY . /app
ARG JAR_FILE
ADD ${JAR_FILE} zappos.jar
RUN echo "Oh dang look at that ${JAR_FILE}"
ENTRYPOINT ["java","-jar","zappos.jar"]