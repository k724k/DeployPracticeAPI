# 🏷️ DeployPracticeAPI 
Spring Boot CRUD 백엔드를 GitHub Actions, Docker, AWS(Route53, ELB, RDS, S3) 기반으로 CI/CD 자동 배포 파이프라인까지 구축한 실습 프로젝트입니다.

## ✅ Overview
- Spring Boot 기반 CRUD 백엔드 API 구현
- GitHub Actions를 활용한 CI/CD 파이프라인 구축
- Docker 기반 컨테이너 이미지 빌드 및 배포
- AWS(Route53, ALB, RDS, S3, ECR)를 활용한 운영 환경 구성

## ✅ Features
- CRUD API 및 RDS(MySQL) 연동을 통한 기본 서비스 구성
- GitHub Actions 기반 자동 빌드·배포 환경 구축
- Docker 컨테이너 기반 배포로 실행 환경 일관성 확보
- ALB + HTTPS 적용으로 안정적인 접근 환경 구성
- S3 연동 이미지 업로드 기능 구현

## ✅ CI/CD 구축 과정

**Step 1. GitHub Actions + EC2 직접 빌드 방식**
- GitHub에 코드 푸시 시 GitHub Actions 워크플로우 실행
- EC2 서버에 접속하여 Git pull 후 애플리케이션 빌드 및 실행
- 배포 흐름과 자동화의 기본 구조를 이해하기 위한 1차 단계
👉 목적: CI/CD 기본 흐름과 서버 직접 배포 방식 이해  
  
<img width="541" height="159" alt="Image" src="https://github.com/user-attachments/assets/61b35b0d-b94a-4e82-b1ab-b4ac621e14a8" />

**Step 2. GitHub Actions → 빌드 파일 전달 방식**
- GitHub Actions에서 애플리케이션 빌드 수행
- 빌드 산출물을 EC2 서버로 전달하여 배포 진행
- 서버에서 직접 빌드하지 않고 빌드 책임을 CI 단계로 분리
👉 목적: 빌드와 실행 환경 분리 개념 학습

<img width="548" height="145" alt="Image" src="https://github.com/user-attachments/assets/c13c71bf-a6f3-4dcc-943e-c3b473498fd2" />

**Step 3. S3 + CodeDeploy 기반 배포 구조**
- GitHub Actions에서 빌드 파일 생성 후 S3에 업로드
- CodeDeploy를 통해 EC2 인스턴스에 배포 명령 전달
- EC2가 S3에서 빌드 파일을 내려받아 배포 수행
👉 목적: AWS 관리형 배포 구조 경험

<img width="548" height="304" alt="Image" src="https://github.com/user-attachments/assets/79dd8179-16bb-4aa1-b68d-4c863e955ea4" />

**Step 4. Docker + ECR 기반 CI/CD**
- GitHub Actions에서 Docker Image 빌드
- Docker Image를 AWS ECR에 업로드
- EC2 서버가 ECR에서 Image를 Pull 받아 컨테이너 실행
👉 목적: 이미지 기반 배포와 환경 일관성 확보

<img width="577" height="303" alt="Image" src="https://github.com/user-attachments/assets/6c5cd0f6-ae08-4dab-ba40-56c5986c03e6" />

## ✅ Tech Stack
<div align="left">
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />&nbsp
</div>
