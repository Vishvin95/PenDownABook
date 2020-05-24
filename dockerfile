FROM amazoncorretto
ADD target/PenDownABook.jar PenDownABook.jar
EXPOSE 8080:9000
VOLUME /tmp
ENTRYPOINT ["java","-jar","PenDownABook.jar"]