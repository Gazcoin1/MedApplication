FROM openjdk:17-jdk-slim
COPY ./build/libs/template-0.0.1.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/medicine
ENV POSTGRES_USER=medicine
ENV POSTGRES_PASSWORD=medicine
EXPOSE 8080
CMD java -jar /opt/service.jar