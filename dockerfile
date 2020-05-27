FROM amazoncorretto
ADD target/PenDownABook-0.0.1-SNAPSHOT.jar /home
WORKDIR /home
EXPOSE 8080:9000
VOLUME /var/log/pendownabook/logs
VOLUME /home/pendownabook/previewbooks
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","PenDownABook-0.0.1-SNAPSHOT.jar"]