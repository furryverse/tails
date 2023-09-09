FROM openjdk:22-jdk
COPY polis/build/libs/milkyway-1.0-SNAPSHOT.jar application.jar
COPY polis/src/main/resources/application.yml application.yml
EXPOSE 10009
ENTRYPOINT ["java","-jar","application.jar"]