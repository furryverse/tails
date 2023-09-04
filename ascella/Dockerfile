FROM openjdk:22-jdk
COPY ascella/build/libs/ascella-1.0-SNAPSHOT.jar application.jar
COPY ascella/src/main/resources/application.yml application.yml
EXPOSE 10002
ENTRYPOINT ["java","-jar","application.jar"]