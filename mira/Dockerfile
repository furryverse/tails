FROM openjdk:22-jdk
COPY mira/build/libs/mira-1.0-SNAPSHOT.jar application.jar
COPY mira/src/main/resources/application.yml application.yml
COPY mira/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10005
ENTRYPOINT ["java","-jar","application.jar"]