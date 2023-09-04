FROM openjdk:22-jdk
COPY nunki/build/libs/nunki-1.0-SNAPSHOT.jar application.jar
COPY nunki/src/main/resources/application.yml application.yml
EXPOSE 10008
ENTRYPOINT ["java","-jar","application.jar"]