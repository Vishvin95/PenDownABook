FROM amazoncorretto
ADD target/PenDownABook-0.0.1-SNAPSHOT.jar /home
WORKDIR /home
EXPOSE 8080:9000
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","PenDownABook-0.0.1-SNAPSHOT.jar"]