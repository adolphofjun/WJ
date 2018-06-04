 --题库表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_bank'))) 
  print '该数据库表已经存在' 
else   
 CREATE TABLE qu_bank
(
id int NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
code varchar(255),
type int,--题库类型
visibility int ,--是否可用
creator int,
creatorTime datetime
)
 
  
 --题目表
 if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_question'))) 
   print '该数据库表已经存在' 
else 
 CREATE TABLE qu_question
(
id int NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
code varchar(255),
type_id int,--题目类型
must int,--是否必选题
bank_id int,--题库ID
visibility int ,--是否可用
isDelete int,--是否可删除
keyWord varchar(255),
creator int,
creatorTime datetime,
mender int,--修改者
menderTime  datetime

)
  
  
  
 --规则表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_rule'))) 
  print '该数据库表已经存在' 
else 
 CREATE TABLE qu_rule
(
id int NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
code varchar(255),
belong_role int,--所属角色
visibility int ,--是否可用
must_bank_id int,--必选题题库
random_bank_id int,--随机题题库
creator int,
creatorTime datetime,
mender int,--修改者
menderTime  datetime
)
 
  
  
   --题目类型表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_type'))) 
  print '该数据库表已经存在' 
else 
 CREATE TABLE qu_type
(
id int NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
code varchar(255),
creator int,
creatorTime datetime,
mender int,--修改者
menderTime  datetime
)

  
  
 --出题规则中的必选题库ID表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_random_rule_id'))) 
  print '该数据库表已经存在' 
else 
 CREATE TABLE qu_random_rule_id
(
id int NOT NULL PRIMARY KEY,
belong_rule int,
qu_bank_id int
)

  
  
 --出题规则中的随机题库ID表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_must_rule_id')))
  print '该数据库表已经存在' 
else 
 CREATE TABLE qu_must_rule_id
(
id int NOT NULL PRIMARY KEY,
belong_rule int,
qu_bank_id int
)

  
  
  
 --选项A表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_select_A')))
	print '数据库表名已经存在' 
else   
  CREATE TABLE qu_select_A
(
id int NOT NULL PRIMARY KEY,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255)
)

 --选项B表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_select_B')))
	print '数据库表名已经存在' 
else   
  CREATE TABLE qu_select_B
(
id int NOT NULL PRIMARY KEY,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255)
)

 --选项C表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_select_C')))
	print '数据库表名已经存在' 
else   
  CREATE TABLE qu_select_C
(
id int NOT NULL PRIMARY KEY,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255)
)

 --选项D表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_select_D')))
	print '数据库表名已经存在' 
else   
  CREATE TABLE qu_select_D
(
id int NOT NULL PRIMARY KEY,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255)
)

 --选项E表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_select_E')))
	print '数据库表名已经存在' 
else   
  CREATE TABLE qu_select_E
(
id int NOT NULL PRIMARY KEY,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255)
)

 --选项F表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_select_F')))
	print '数据库表名已经存在' 
else   
  CREATE TABLE qu_select_F
(
id int NOT NULL PRIMARY KEY,
name varchar(255),
code varchar(255),
score float,
belong_qu int,
title varchar(255)
)


 --问卷表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('qu_questionnaire'))) 
  print '该数据库表已经存在' 
else 
 CREATE TABLE qu_questionnaire
(
id int NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
code varchar(255),
visibility int ,--是否可用
creator int,
creatorTime datetime,
mender int,--修改者
menderTime  datetime,
qu_id varchar(1000)--问卷对应的题目ID
)

 --员工接受到的问卷表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('sys_emp_qu'))) 
  print '该数据库表已经存在' 
else 
 CREATE TABLE sys_emp_qu
(
id int NOT NULL PRIMARY KEY,
qu_id int ,--对应的问卷
creator int,
score float,
must_score float,
random_score int,
an_time datetime,
creatorTime datetime,
mender int,--修改者
menderTime  datetime,
qu_type int--问卷类型
)


 --员工接受到的问卷对应的题目表
if(Exists(select * from sys.sysobjects where id=OBJECT_ID('sys_emp_qu_qu'))) 
  print '该数据库表已经存在' 
else 
 CREATE TABLE sys_emp_qu_qu
(
id int NOT NULL PRIMARY KEY,
emp_id int ,--对应的问卷
qu_id int,
qu_qu_id int,
score float
)
  
  
  