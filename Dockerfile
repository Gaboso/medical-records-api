FROM adoptopenjdk/openjdk11:alpine
COPY ./target/medical-record-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "medical-record-0.0.1-SNAPSHOT.war"]