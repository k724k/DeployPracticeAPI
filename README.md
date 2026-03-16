# 🏷️ DeployPracticeAPI

Spring Boot 기반 CRUD 백엔드를 **GitHub Actions, Docker, AWS** 환경에서 CI/CD 자동 배포까지 경험할 수 있는 실습 프로젝트입니다.  
AWS Route53, ALB, RDS, S3를 활용하여 운영 환경을 구성하고, GitHub Actions와 Docker를 활용한 자동화 배포 파이프라인을 구현했습니다.

---

# 📌 Project Overview

- **프로젝트 유형:** 개인 실습 프로젝트  
- **목표:** CI/CD 자동화 구축, Docker 기반 배포 환경 이해, AWS 관리형 서비스 활용  
- **기간:** 2025.08 ~ 2025.11  
- **주요 학습 포인트:**  
  - CI/CD 파이프라인 설계 및 자동화  
  - Docker 기반 이미지 관리 및 배포  
  - AWS 서비스(RDS, ALB, S3, Route53, ECR) 기반 운영 환경 구성  

---

# 🔧 Key Features

### 1️⃣ CRUD Backend API
- Spring Boot + Spring Data JPA 기반 CRUD API 구현  
- MySQL RDS 연동을 통한 데이터 저장 및 조회

### 2️⃣ CI/CD 자동화
- GitHub Actions 워크플로우 구성  
- Docker 컨테이너 기반 빌드 및 배포  
- AWS EC2 + ALB + HTTPS 환경 구성

### 3️⃣ 이미지 및 정적 파일 관리
- S3 연동 이미지 업로드 기능 구현  
- ALB와 HTTPS 적용으로 안정적인 접근 환경 제공

---

# 🔄 CI/CD 구축 과정

### Step 1. GitHub Actions + EC2 직접 빌드
- GitHub에 코드 푸시 시 GitHub Actions 실행
- EC2에서 Git pull 후 애플리케이션 빌드 및 실행
- **목적:** CI/CD 기본 구조와 서버 직접 배포 방식 이해

<img width="541" height="159" alt="Step 1" src="https://github.com/user-attachments/assets/61b35b0d-b94a-4e82-b1ab-b4ac621e14a8" />

---

### Step 2. GitHub Actions → 빌드 산출물 전달
- GitHub Actions에서 빌드 수행 후 EC2로 전달
- EC2 서버에서 직접 빌드하지 않고 CI 단계에서 처리
- **목적:** 빌드와 실행 환경 분리 개념 학습

<img width="548" height="145" alt="Step 2" src="https://github.com/user-attachments/assets/c13c71bf-a6f3-4dcc-943e-c3b473498fd2" />

---

### Step 3. S3 + CodeDeploy 기반 배포
- 빌드 파일을 S3에 업로드 후 CodeDeploy로 EC2에 배포
- EC2가 S3에서 빌드 파일을 내려받아 애플리케이션 실행
- **목적:** AWS 관리형 배포 구조 경험

<img width="548" height="304" alt="Step 3" src="https://github.com/user-attachments/assets/79dd8179-16bb-4aa1-b68d-4c863e955ea4" />

---

### Step 4. Docker + ECR 기반 CI/CD
- GitHub Actions에서 Docker 이미지 빌드 후 AWS ECR에 업로드
- EC2 서버에서 ECR 이미지 Pull 후 컨테이너 실행
- **목적:** 이미지 기반 배포와 환경 일관성 확보

<img width="577" height="303" alt="Step 4" src="https://github.com/user-attachments/assets/6c5cd0f6-ae08-4dab-ba40-56c5986c03e6" />

---

# ⚙️ Tech Stack

<div align="left">
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
</div>
