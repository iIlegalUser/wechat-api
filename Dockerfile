FROM openjdk:17
WORKDIR /app
COPY jars/ /app
EXPOSE 8950
CMD java -jar eureka-0.1.jar gateway-0.1.jar news-0.1.jar user-0.1.jar
