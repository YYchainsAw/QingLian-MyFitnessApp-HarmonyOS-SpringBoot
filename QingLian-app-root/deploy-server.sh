#!/bin/bash

# 1. 检查文件是否存在
if [ ! -f "QingLian-app-root-1.0-SNAPSHOT.jar" ]; then
    echo "错误：未找到 QingLian-app-root-1.0-SNAPSHOT.jar"
    echo "请确保你已经上传了 target 目录下的 jar 包到当前目录，或者修改 Dockerfile 的 COPY 路径"
    exit 1
fi

en# 2. 在服务器端构建镜像
echo "正在服务器上构建 Docker 镜像..."
docker build -t qinglian-api . || { echo "Docker 构建失败"; exit 1; }

# 3. 停止并删除旧容器 (Stop and remove old container)
echo "正在停止旧容器..."
docker stop qinglian-api 2>/dev/null
docker rm qinglian-api 2>/dev/null

# 4. 启动新容器 (Run new container)
echo "正在启动新容器..."

# 注意：
# - 这里使用了 --add-host=host.docker.internal:host-gateway 让容器可以访问宿主机(服务器)的数据库
# - 请修改下面的 YOUR_DB_PASSWORD_HERE 为你服务器上 PostgreSQL 的真实密码
# - 如果数据库用户名不是 postgres，也请修改
# - 如果 Redis 有密码，请添加 -e SPRING_DATA_REDIS_PASSWORD="你的密码"

docker run -d \
  --name qinglian-api \
  --restart always \
  -p 8080:8080 \
  --add-host=host.docker.internal:host-gateway \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5432/Myfitness_App" \
  -e SPRING_DATASOURCE_USERNAME="postgres" \
  -e SPRING_DATASOURCE_PASSWORD="WLKyMa7CMdXJtZhF" \
  -e SPRING_DATA_REDIS_HOST="host.docker.internal" \
  qinglian-api

echo "部署完成！查看日志："
docker logs --tail 20 qinglian-api
