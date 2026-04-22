# 使用 Microsoft OpenJDK 17 (基于 Mariner/CBL-Mariner Linux，微软官方源，国内通常比 Docker Hub 稳定)
FROM mcr.microsoft.com/openjdk/jdk:17-mariner

WORKDIR /app

# 复制 Jar 包
# 注意：部署时请将 jar 包和 Dockerfile 放在同一级目录，或者根据实际情况修改路径
COPY QingLian-app-root-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
