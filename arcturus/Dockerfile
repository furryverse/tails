FROM openjdk:22-jdk
COPY arcturus/build/libs/arcturus-1.0-SNAPSHOT.jar application.jar
COPY arcturus/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10001
ENTRYPOINT ["java","-jar","application.jar"]