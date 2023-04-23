#!/bin/bash

echo -e "\033[33m

██████╗  ██████╗ █████╗ 
██╔══██╗██╔════╝██╔══██╗
██║  ██║██║     ███████║
██║  ██║██║     ██╔══██║
██████╔╝╚██████╗██║  ██║
╚═════╝  ╚═════╝╚═╝  ╚═╝          

\033[0m"

Convert_Num() {
    if [[ $2 =~ ^[0-9]*$ ]]
    then
        sed -i "s/^$1.*$/$1 = $2/" ../.env
    else
        echo -e "\n$1 setting Error!"
        exit 1
    fi
}

Convert() {
    if [[ ! $2 =~ ^[0-9] ]]
    then
        sed -i "s/^$1.*$/$1 = $2/" ../.env
    else
        echo -e "\n$1 setting Error!"
        exit 1
    fi
}

echo -e "\033[34mDocker Compose Port Setting \033[0m"

read -p "SpringBoot port : " SPRINGBOOT_PORT
read -p "Flask port : " FLASK_PORT
read -p "MariaDB port : " MARIADB_PORT

Convert_Num "SPRINGBOOT_PORT" $SPRINGBOOT_PORT
Convert_Num "FLASK_PORT" $FLASK_PORT
Convert_Num "MARIADB_PORT" $MARIADB_PORT

echo -e "\n\033[31mMariaDB Setting \033[0m"

read -p "User : " MARIADB_USER
read -p "Database Name : " MARIADB_DATABASE
read -p "Root Passwd : " MARIADB_ROOT_PASSWORD
read -p "Passwd : " MARIADB_PASSWORD

Convert "MARIADB_USER" $MARIADB_USER
Convert "MARIADB_DATABASE" $MARIADB_DATABASE
Convert "MARIADB_ROOT_PASSWORD" $MARIADB_ROOT_PASSWORD
Convert "MARIADB_PASSWORD" $MARIADB_PASSWORD