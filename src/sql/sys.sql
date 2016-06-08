SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;

#用户表,建表请按这张项目表格式建，注释要加上
/*用户表*/
DROP TABLE if EXISTS user;
CREATE TABLE user(
  id  INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userName  VARCHAR(255) DEFAULT NULL COMMENT '用户名',
  password  VARCHAR(255) DEFAULT NULL COMMENT '用户密码',
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户表';
