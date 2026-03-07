# Spring Boot Deployment Guide

### 1. Build the Artifact (JAR Packaging)
Package the application into a single executable JAR file using Maven.

    mvn clean package -DskipTests

Creates: target/app-v1.0.jar


### 2. Dockerization (Container Deployment)

Create a Dockerfile to wrap the JAR in a lightweight Linux environment.
    /dockerfile
        FROM eclipse-temurin:17-jdk-alpine
        COPY target/*.jar app.jar
        ENTRYPOINT ["java", "-jar", "/app.jar"]
        Use code with caution.

Build and Run:
    bash
        docker build -t my-spring-app .
        docker run -p 8080:8080 my-spring-app
        Use code with caution.

### 3. Cloud Deployment (AWS/Azure)

    PaaS: Upload the .jar to AWS Elastic Beanstalk.
    Serverless: Deploy as an AWS Lambda function for cost-saving.
    Containerized: Push the image to AWS ECR and run via ECS/Fargate.

### 4. Health & Monitoring

    Once deployed, verify the application status via Actuator:
    Health: GET /actuator/health
    Metrics: GET /actuator/metrics

