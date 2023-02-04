#
# Package stage
#
FROM openjdk:17-slim
ADD target/java-template-0.0.1-SNAPSHOT.jar /usr/local/lib/jars/hackathon-service.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/usr/local/lib/jars/hackathon-service.jar"]
