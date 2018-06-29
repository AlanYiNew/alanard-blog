#!/bin/sh
today=$(date +%Y-%m-%d)
mysqldump --databases alanard > ~/backup/db-$today.sql
tarfile=databackup-$today.sql.tar.gz
file=backup/db-$today.sql
tar -czf $tarfile $file
echo "主题:数据库备份" | mutt -s "内容:数据库备份" 245655664@qq.com -a databackup-$today.sql.tar.gz
rm -rf databackup-$today.sql.tar.gz