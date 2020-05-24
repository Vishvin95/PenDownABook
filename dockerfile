FROM amazoncorretto
ADD target/PenDownABook-0.0.1-SNAPSHOT.jar PenDownABook-0.0.1-SNAPSHOT.jar
EXPOSE 8080:9000
VOLUME /tmp
ENTRYPOINT ["java","-jar","PenDownABook-0.0.1-SNAPSHOT.jar"]