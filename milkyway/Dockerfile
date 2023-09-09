FROM openjdk:22-jdk
COPY milkyway/build/libs/milkyway-1.0-SNAPSHOT.jar application.jar
COPY milkyway/src/main/resources/application.yml application.yml
EXPOSE 10009
ENTRYPOINT ["java","-jar","application.jar"]