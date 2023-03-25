FROM openjdk:11-jre-slim
ARG BUILD_PATH
COPY ./${BUILD_PATH}/target/*.jar app.jar
CMD java -jar /app.jar