DELETE FROM mysql.user WHERE User="prosoftAngle";
DROP DATABASE IF EXISTS godb;
CREATE DATABASE godb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON locksdb.* TO prosoftAngle@'localhost' IDENTIFIED BY 'pro-softAngle123';
GRANT ALL PRIVILEGES ON locksdb.* TO prosoftAngle@'127.0.0.1' IDENTIFIED BY 'pro-softAngle123';
GRANT ALL PRIVILEGES ON locksdb.* TO prosoftAngle@'%' IDENTIFIED BY 'pro-softAngle123';

