#!/bin/sh

echo "start blacklist.sh"

if [ $# -lt 1 ]
then
    echo "sh blacklist.sh <filename>"
else
    ip_list=$1
    
    while read ip; do
        # echo $ip
        iptables -A INPUT -s $ip -j DROP
    done < $ip_list
fi