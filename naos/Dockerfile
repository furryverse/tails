FROM openjdk:22-jdk
COPY naos/build/libs/naos-1.0-SNAPSHOT.jar application.jar
COPY naos/src/main/resources/application.yml application.yml
EXPOSE 10007
ENTRYPOINT ["java","-jar","application.jar"]