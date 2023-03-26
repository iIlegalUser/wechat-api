FROM openjdk:17
WORKDIR /app
COPY jars/ /app
ADD start.sh /app
EXPOSE 8950
CMD ./start.sh
