FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/service_class.jar service_class.jar
ENTRYPOINT ["java","-jar","/service_class.jar", "&"]
