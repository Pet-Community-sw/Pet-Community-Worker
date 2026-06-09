#!/usr/bin/env bash
set -e # 오류 시 스크립트 종료

DEBEZIUM_USER="${DEBEZIUM_USER:-debezium}"
DEBEZIUM_PASSWORD="${DEBEZIUM_PASSWORD:-debezium}"
MYSQL_ROOT_PASSWORD="${MYSQL_ROOT_PASSWORD:-root}"

echo "1. Debezium 계정 생성 및 권한 부여"
docker exec -i pet-community-api-mysql-1 mysql -uroot -p"$MYSQL_ROOT_PASSWORD" <<SQL
CREATE USER IF NOT EXISTS '$DEBEZIUM_USER'@'%' IDENTIFIED BY '$DEBEZIUM_PASSWORD';
GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT
ON *.* TO '$DEBEZIUM_USER'@'%';
FLUSH PRIVILEGES;
SQL

echo "2. docker compose 실행"
docker compose up -d

echo "3. boot 실행"
./gradlew bootRun