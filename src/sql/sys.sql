SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;

#用户表,建表请按这张项目表格式建，注释要加上
/*用户表*/
DROP TABLE if EXISTS auth_user;
CREATE TABLE auth_user(
  id  INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userName  VARCHAR(255) DEFAULT NULL COMMENT '用户名',
  password  VARCHAR(255) DEFAULT NULL COMMENT '用户密码',
  isAdmin  INT DEFAULT 0 COMMENT '0否，1是'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户表';


DROP TABLE if EXISTS auth_sysAuth;
CREATE TABLE auth_sysAuth(
  id  INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  key VARCHAR(255) DEFAULT NULL COMMENT '权限key',
  url  VARCHAR(255) DEFAULT NULL COMMENT '请求url',
  parent  INT DEFAULT 0 COMMENT '0父级,无URL只做是否显示判断'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='系统权限表';

DROP TABLE if EXISTS auth_user_auid;
CREATE TABLE auth_user_auid(
  userid  INT DEFAULT 0 COMMENT '请求url' PRIMARY KEY,
  authid  INT DEFAULT 0 COMMENT '权限id' PRIMARY KEY
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户权限关联表';