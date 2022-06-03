FROM openjdk:8
EXPOSE 8084
ADD target/product-app-devops.jar app.jar
ENTRYPOINT [ "java" , "-jar" , "/app.jar"]