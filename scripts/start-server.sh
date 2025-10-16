#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
docker stop deploypractice-server || true
docker rm deploypractice-server || true
docker pull 827859361304.dkr.ecr.ap-northeast-2.amazonaws.com/deploypractice-server:latest
docker run -d --name deploypractice-server -p 8080:8080 827859361304.dkr.ecr.ap-northeast-2.amazonaws.com/deploypractice-server:latest
echo "--------------- 서버 배포 끝 -----------------"