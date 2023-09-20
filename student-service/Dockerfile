FROM openjdk
LABEL maintainer = "abc@mail.com"
EXPOSE 8084
WORKDIR /app
COPY target/studentservice.jar /app/studentservice.jar
ENTRYPOINT ["java","-jar","studentservice.jar"]