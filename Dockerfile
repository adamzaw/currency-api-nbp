FROM openjdk:8
ADD target/api-nbp.jar api-nbp.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "api-nbp.jar"]