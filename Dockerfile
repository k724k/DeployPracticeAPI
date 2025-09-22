
# 1. 빌드 스테이지: Gradle 빌드 실행
FROM gradle:7.6.1-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon

# 2. 런타임 스테이지: 빌드된 jar를 복사해서 애플리케이션 실행
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
