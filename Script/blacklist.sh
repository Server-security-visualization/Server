#!/bin/bash

echo "start blacklist.sh"

if [ $# -lt 1 ]
then
    echo "sh blacklist.sh <filename>"
else
    ip_list=$1
    
    while read ip; do
        if [[ $ip =~ ^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$ ]]
        then
            iptables -A INPUT -s $ip -j DROP
        else
            echo "Error $ip"
        fi
    done < $ip_list
fi

echo "finish"