 /*题库表*/
CREATE TABLE IF NOT EXISTS `qu_bank`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) NOT NULL,
   `code` VARCHAR(40) NOT NULL,
   `type` int,
	 `isUser` int,
   `visibility` int,
	 `creator` int,
   `creatorTime` DATETIME,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
  
 /*--题目表*/
 CREATE TABLE IF NOT EXISTS `qu_question`(
   id INT UNSIGNED AUTO_INCREMENT,
  name varchar(255) NOT NULL,
code varchar(255),
type_id int,/*--题目类型*/
must int,/*--是否必选题*/
bank_id int,/*--题库ID*/
visibility int ,/*--是否可用*/
isDelete int,/*--是否可删除*/
keyWord varchar(255),
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
  
 /*--规则表*/
CREATE TABLE IF NOT EXISTS `qu_rule`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255) NOT NULL,
code varchar(255),
belong_role int,/*--所属角色*/
visibility int ,/*--是否可用*/
must_bank_id int,/*--必选题题库*/
random_bank_id int,/*--随机题题库*/
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
  
  
   /*--题目类型表*/
CREATE TABLE IF NOT EXISTS `qu_type`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255) NOT NULL,
code varchar(255),
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

  
  
 /*--出题规则中的必选题库ID表*/
CREATE TABLE IF NOT EXISTS `qu_random_rule_id`(
   id INT UNSIGNED AUTO_INCREMENT,
belong_rule int,
qu_bank_id int,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

  
  
 /*--出题规则中的随机题库ID表*/
CREATE TABLE IF NOT EXISTS `qu_must_rule_id`(
   id INT UNSIGNED AUTO_INCREMENT,
belong_rule int,
qu_bank_id int,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

  
  
  
 /*--选项A表*/
CREATE TABLE IF NOT EXISTS `qu_select_A`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--选项B表*/
CREATE TABLE IF NOT EXISTS `qu_select_B`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--选项C表*/
CREATE TABLE IF NOT EXISTS `qu_select_C`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--选项D表*/
CREATE TABLE IF NOT EXISTS `qu_select_D`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--选项E表*/
CREATE TABLE IF NOT EXISTS `qu_select_E`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--选项F表*/
CREATE TABLE IF NOT EXISTS `qu_select_F`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--问卷表表头*/
CREATE TABLE IF NOT EXISTS `sys_emp_qu_title`(
   id INT UNSIGNED AUTO_INCREMENT,
qu_id int ,/*--//对应的问卷*/
targetId int,
answerId int,
score float,/*最终分*/
an_time datetime,
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
qu_type int,/*--问卷类型*/
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 /*--问卷表*/
CREATE TABLE IF NOT EXISTS `qu_questionnaire`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255) NOT NULL,
code varchar(255),
visibility int ,/*--是否可用*/
creator int,
isUser int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
qu_id varchar(1000),/*--问卷对应的题目ID*/
rule_id int,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 /*--可查看问卷的角色*/
CREATE TABLE IF NOT EXISTS `qu_questionnaire_role`(
   id INT UNSIGNED AUTO_INCREMENT,
role_id int,
questionnaire_id int,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--员工接受到的问卷表*/
CREATE TABLE IF NOT EXISTS `sys_emp_qu`(
   id INT UNSIGNED AUTO_INCREMENT,
qu_id int ,/*--对应的问卷*/
score float,
must_score float,
random_score int,
an_time datetime,
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
qu_type int,/*--问卷类型*/
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 /*--员工接受到的问卷对应的题目表*/
CREATE TABLE IF NOT EXISTS `sys_emp_qu_qu`(
   id INT UNSIGNED AUTO_INCREMENT,
emp_id int ,/*--对应的员工*/
qu_id int,
qu_qu_id int,
score float,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 /*--员工信息表*/
CREATE TABLE IF NOT EXISTS `sys_emp`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
pwd  varchar(255),
sex int,
phone varchar(20),
address varchar(255),
idCard varchar(20),
email varchar(255),
education varchar(255),
school varchar(255),
orginId int,
deptId int,
creator int,
userType int,
visibility int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--角色信息表*/
CREATE TABLE IF NOT EXISTS `sys_role`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
power varchar(255),
orginId int,
creator int,
userType int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--用户角色信息表*/
CREATE TABLE IF NOT EXISTS `sys_user_role`(
   id INT UNSIGNED AUTO_INCREMENT,
userId int,
roleId int,
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 --部门信息表
CREATE TABLE IF NOT EXISTS `sys_dept`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
isUser int,
visibility int,
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*20180614问卷头*/
CREATE TABLE IF NOT EXISTS `wj_title`(
   id INT UNSIGNED AUTO_INCREMENT,
nane int,
code VARCHAR(100),
isUser int,
visibility int,
ruleName VARCHAR(100),
quType int,
isMust int,
creator int,
creatorTime datetime,
mender int,/*--修改者*/
menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*20180614--选项A表*/
CREATE TABLE IF NOT EXISTS `wj_select_A`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_wj int,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项B表*/
CREATE TABLE IF NOT EXISTS `wj_select_B`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_wj int,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项C表*/
CREATE TABLE IF NOT EXISTS `wj_select_C`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_wj int,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项D表*/
CREATE TABLE IF NOT EXISTS `wj_select_D`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_wj int,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项E表*/
CREATE TABLE IF NOT EXISTS `wj_select_E`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_wj int,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项F表*/
CREATE TABLE IF NOT EXISTS `wj_select_F`(
   id INT UNSIGNED AUTO_INCREMENT,
name varchar(255),
code varchar(255),
score float,
belong_wj int,
belong_qu int,
title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*20180614*/
alter table wj_title add ruleId int


/*20180613*/
alter table qu_questionnaire  add num int;
alter table qu_random_rule_id  add per float;
alter table qu_must_rule_id  add per float;
alter table qu_rule  add num int;
alter table sys_emp_qu  add targetId int;
alter table sys_emp_qu  add answerId int;
