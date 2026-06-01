# 🐶 멍냥로드 Worker Server

Worker Server는 API 서버에서 발생한 비동기 이벤트를 처리하는 서버입니다.

---

## 기술 스택

- Language: Java
- Framework: Spring Boot
- Message Broker: RabbitMQ
- CDC: Debezium
- Database: MySQL, Redis, Elasticsearch
- Cloud: AWS EC2, RDS
- CI/CD: GitHub Actions

---

## 시작하기

### 사전 요구사항

- Java 21
- Docker

### 설치 방법

### 1. 저장소 클론

```bash
git clone https://github.com/Pet-Community-sw/Pet-Community-Worker.git
cd Pet-Community-Worker
```

### 2. 스크립트 실행

```bash
bash ./init-script.sh
```

---

## System Architecture

<img width="634" height="544" alt="System Architecture" src="https://github.com/user-attachments/assets/7358e8bb-6fb8-4106-ba59-e8ec2112fb29" />

---

## CI/CD Architecture

<img width="1004" height="506" alt="CI:CD Architecture" src="https://github.com/user-attachments/assets/ee4b8682-2f61-4c59-b3b6-c74994b7eda1" />