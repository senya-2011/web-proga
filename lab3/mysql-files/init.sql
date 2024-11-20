CREATE DATABASE IF NOT EXISTS lab3db;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON lab3db.* TO 'user'@'%';
FLUSH PRIVILEGES;