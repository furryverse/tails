FROM openjdk:22-jdk
COPY hecatebolus/build/libs/hecatebolus-1.0-SNAPSHOT.jar application.jar
COPY hecatebolus/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10004
ENTRYPOINT ["java","-jar","application.jar"]