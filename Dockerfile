# Runtime-only image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the fat jar built by Maven (Spring Boot plugin)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
# Sensible defaults; tweak if you want
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
