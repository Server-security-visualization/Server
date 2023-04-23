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
        sed -i "s/^$1.*$/$1 = $2/" $3
    else
        echo -e "\n$1 setting Error!"
        exit 1
    fi
}

Convert() {
    if [[ ! $2 =~ ^[0-9] ]]
    then
        sed -i "s/^$1.*$/$1 = $2/" $3
    else
        echo -e "\n$1 setting Error!"
        exit 1
    fi
}

echo -e "\033[34mDocker Compose Port Setting \033[0m"

read -p "SpringBoot port : " SPRINGBOOT_PORT
read -p "Flask port : " FLASK_PORT
read -p "MariaDB port : " MARIADB_PORT

Convert_Num "SPRINGBOOT_PORT" $SPRINGBOOT_PORT "../.env"
Convert_Num "FLASK_PORT" $FLASK_PORT "../.env"
Convert_Num "MARIADB_PORT" $MARIADB_PORT "../.env"
Convert_Num "port" $MARIADB_PORT "../Flask/config.ini"

echo -e "\n\033[31mMariaDB Setting \033[0m"

read -p "Database Name : " MARIADB_DATABASE
read -p "User : " MARIADB_USER
read -p "Passwd : " MARIADB_PASSWORD
read -p "Root Passwd : " MARIADB_ROOT_PASSWORD

Convert "MARIADB_DATABASE" $MARIADB_DATABASE "../.env"
Convert "database" $MARIADB_DATABASE "../Flask/config.ini"

Convert "MARIADB_USER" $MARIADB_USER "../.env"
Convert "user" $MARIADB_USER "../Flask/config.ini"

Convert "MARIADB_PASSWORD" $MARIADB_PASSWORD "../.env"
Convert "password" $MARIADB_PASSWORD "../Flask/config.ini"

Convert "MARIADB_ROOT_PASSWORD" $MARIADB_ROOT_PASSWORD "../.env"

echo -e "\033[33mAI Model Download \033[0m"

mkdir ../Flask/model/malware_cnn

wget --load-cookies ~/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies ~/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1EJ1vEYWZ9d0LUV0e_OrC0OUBWXnZBP9y' -O- | sed -rn 's/.*confirm=([0-9A-Za-z_]+).*/\1\n/p')&id=1EJ1vEYWZ9d0LUV0e_OrC0OUBWXnZBP9y" -O ./malware_cnn.zip && rm -rf ~/cookies.txt && unzip ./malware_cnn.zip -d ../Flask/model/malware_cnn && rm -rf ./malware_cnn.zip

mkdir ../Flask/model/webattack_detection_rf_model

wget --load-cookies ~/cookies.txt "https://docs.google.com/uc?export=download&confirm=$(wget --quiet --save-cookies ~/cookies.txt --keep-session-cookies --no-check-certificate 'https://docs.google.com/uc?export=download&id=1SmXacuskK4MFwloh3HsALnlypggk9jAv' -O- | sed -rn 's/.*confirm=([0-9A-Za-z_]+).*/\1\n/p')&id=1SmXacuskK4MFwloh3HsALnlypggk9jAv" -O ./webattack_detection_rf_model.zip && rm -rf ~/cookies.txt && unzip ./webattack_detection_rf_model.zip -d ../Flask/model/webattack_detection_rf_model && rm -rf ./webattack_detection_rf_model.zip

echo -e "\033[33mEND!!\033[0m"