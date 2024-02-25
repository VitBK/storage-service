FROM eclipse-temurin:21.0.1_12-jre-jammy
RUN mkdir /app
COPY build/libs/storage-service.jar /app/
EXPOSE 8084 8084
ENTRYPOINT ["java","-jar","/app/storage-service.jar"]