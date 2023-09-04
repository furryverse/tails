FROM openjdk:22-jdk
COPY nanto/build/libs/nanto-1.0-SNAPSHOT.jar application.jar
COPY nanto/src/main/resources/application.yml application.yml
COPY nanto/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10006
ENTRYPOINT ["java","-jar","application.jar"]