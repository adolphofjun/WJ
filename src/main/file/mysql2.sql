/*题库表*/
CREATE TABLE IF NOT EXISTS `qu_bank`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) NOT NULL,
   `code` VARCHAR(40) NOT NULL,
   `type` int,
   `isUser` int DEFAULT 0,
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
	maxScore float,/*题目选项中最高分*/
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



 /*--规则表*/
CREATE TABLE IF NOT EXISTS `qu_rule`(
   id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	code varchar(255),
	belong_role int,/*--被问卷角色*/
	visibility int ,/*--是否可用*/
	must_bank_id int,/*--必选题题库*/
	random_bank_id int,/*--随机题题库*/
	type int,,/*--单人还是多人*/
	creator int,
	num int,/*--改规则下有多少题目*/
	creatorTime datetime,
	mender int,/*--修改者*/
	menderTime  datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*--规则对象--被问卷对象*/
CREATE TABLE IF NOT EXISTS `qu_rule_emp`(
   id INT UNSIGNED AUTO_INCREMENT,
   empId int,
   ruleId int,
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
	 per float,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



 /*--出题规则中的随机题库ID表*/
CREATE TABLE IF NOT EXISTS `qu_must_rule_id`(
   id INT UNSIGNED AUTO_INCREMENT,
	belong_rule int,
	qu_bank_id int,
	per float,
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
CREATE TABLE IF NOT EXISTS `wj_title`(
	id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255),
  code varchar(255),
	qu_id int ,/*--//对应的问卷*/
	sumScore float,/*问卷总分*/
	num int,/*问卷题目总数*/
	visibility int,/*是否启用*/
	isDelete int,/*是否可以删除*/
	startTime  datetime,
	endTime  datetime,
	creator int,
	creatorTime datetime,
	mender int,/*--修改者*/
	menderTime  datetime,
	qu_type int,/*--问卷类型*/
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--答题员工中间表*/
CREATE TABLE IF NOT EXISTS `wj_answer_emp`(
	id INT UNSIGNED AUTO_INCREMENT,
  empName varchar(255) NOT NULL,/*问卷对象姓名*/
  empId int,
	wjId int,/*问卷Id*/
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 /*--问卷对象中间表*/
CREATE TABLE IF NOT EXISTS `wj_target_emp`(
	id INT UNSIGNED AUTO_INCREMENT,
  empName varchar(255) NOT NULL,/*问卷对象姓名*/
  empId int,
	wjId int,/*问卷Id*/
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


 /*--问卷题目表*/
CREATE TABLE IF NOT EXISTS `wj_question`(
	id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	code varchar(255),
	wjId int,/*--属于那张问卷*/
	type_id int,/*--题目类型*/
	must int,/*--是否必选题*/
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
	belong_wj_qu int,/*属于问卷中的那个题目*/
	title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项B表*/
CREATE TABLE IF NOT EXISTS `wj_select_B`(
   id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255),
	code varchar(255),
	score float,
	belong_wj_qu int,/*属于问卷中的那个题目*/
	title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项C表*/
CREATE TABLE IF NOT EXISTS `wj_select_C`(
   id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255),
	code varchar(255),
	score float,
	belong_wj_qu int,/*属于问卷中的那个题目*/
	title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项D表*/
CREATE TABLE IF NOT EXISTS `wj_select_D`(
   id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255),
	code varchar(255),
	score float,
	belong_wj_qu int,/*属于问卷中的那个题目*/
	title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项E表*/
CREATE TABLE IF NOT EXISTS `wj_select_E`(
   id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255),
	code varchar(255),
	score float,
	belong_wj_qu int,/*属于问卷中的那个题目*/
	title varchar(255),
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*20180614--选项F表*/
CREATE TABLE IF NOT EXISTS `wj_select_F`(
   id INT UNSIGNED AUTO_INCREMENT,
	name varchar(255),
	code varchar(255),
	score float,
	belong_wj_qu int,/*属于问卷中的那个题目*/
	title varchar(255),
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

 /*--中间表--员工答题过的问卷-关联wj_record_everyone*/
CREATE TABLE IF NOT EXISTS `sys_emp_wj_ans`(
   id INT UNSIGNED AUTO_INCREMENT,
	empId int,
	wjId int,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*--中间表--员工作为被答题者的问卷-关联wj_record_sum*/
CREATE TABLE IF NOT EXISTS `sys_emp_wj_target`(
   id INT UNSIGNED AUTO_INCREMENT,
	empId int,
	wjId int,
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

 /*--中间表-用户角色信息表*/
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


 #--部门信息表
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

/*计分表-每个人答题记录*/
CREATE TABLE IF NOT EXISTS `wj_record_everyone`(
   id INT UNSIGNED AUTO_INCREMENT,
	answerId int,
	targetId int,
	wjId int,
	score float,
	sumScore float,
	num int,
	answerTime datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*计分表-被答题者的总分记录*/
CREATE TABLE IF NOT EXISTS `wj_record_sum`(
   id INT UNSIGNED AUTO_INCREMENT,
	targetId int,
	wjId int,
	ansNum int,
	score float,
	answerTime datetime,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


