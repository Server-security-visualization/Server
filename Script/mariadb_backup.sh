#!/bin/bash

DATE=$(date +%Y%m%d)
mkdir ../MariaDB/Backup
BACKUP_DIR=../MariaDB/Backup
DOCKER_MARIADB_NAME="dca-mariadb"

MARIADB_USER=$(sed -n -e "s/^MARIADB_USER\s\?=\s\?//p" ../.env)
MARIADB_PASSWORD=$(sed -n -e "s/^MARIADB_PASSWORD\s\?=\s\?//p" ../.env)
MARIADB_DATABASE=$(sed -n -e "s/^MARIADB_DATABASE\s\?=\s\?//p" ../.env)

docker exec $DOCKER_MARIADB_NAME /usr/bin/mysqldump -u $MARIADB_USER --password=$MARIADB_PASSWORD $MARIADB_DATABASE > $BACKUP_DIR"/"$DATE.sql