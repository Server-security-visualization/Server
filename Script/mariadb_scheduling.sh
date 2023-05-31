BACKUP_SH = mariadb_backup.sh

# 매주 월요일 0시 0분에 실행
echo "0 0 * * 1 root $PWD"/"$BACKUP_SH" >> /etc/crontab

chmod -R 755 $PWD"/"$BACKUP_SH"
sudo systemctl restart crond