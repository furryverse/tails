FROM openjdk:22-jdk
COPY borealis/build/libs/borealis-1.0-SNAPSHOT.jar application.jar
COPY borealis/src/main/resources/application.yml application.yml
COPY borealis/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10003
ENTRYPOINT ["java","-jar","application.jar"]