FROM openjdk:22-jdk
COPY alnitak/build/libs/alnitak-1.0-SNAPSHOT.jar application.jar
COPY alnitak/src/main/resources/application.yml application.yml
COPY alnitak/src/main/resources/bootstrap.yml bootstrap.yml
EXPOSE 10000
ENTRYPOINT ["java","-jar","application.jar"]