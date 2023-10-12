FROM openjdk:22-jdk
COPY polis/build/libs/polis-1.0-SNAPSHOT.jar application.jar
COPY polis/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10009
ENTRYPOINT ["java","-jar","application.jar"]