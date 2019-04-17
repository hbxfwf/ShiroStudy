/*
SQLyog Enterprise v12.5.0 (64 bit)
MySQL - 5.5.56 : Database - shiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`) values 
(1,'小说类'),
(2,'程序开发类');

/*Table structure for table `classes` */

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `classes` */

insert  into `classes`(`cid`,`cname`) values 
(1,'1301班'),
(2,'1302班'),
(3,'1303班');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`cid`,`cname`) values 
(1,'C语言'),
(2,'Java'),
(3,'C++');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `addr` varchar(100) DEFAULT '深圳',
  `cid` int(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `fk_01` (`cid`),
  CONSTRAINT `fk_01` FOREIGN KEY (`cid`) REFERENCES `classes` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`sid`,`sname`,`sex`,`age`,`addr`,`cid`,`birth`) values 
(1,'张三','男',20,'上海',1,'1995-07-13'),
(2,'五二','男',28,'广州',1,'2000-07-20'),
(3,'小红','女',19,'杭州',2,'1995-07-13'),
(4,'王二小','男',12,'岳阳',2,'1986-06-11'),
(5,'小2添','女',18,'襄阳',2,'1989-03-23'),
(6,'小黄','男',21,'南阳',1,'1987-05-17'),
(7,'罗成','男',22,'邵阳',1,'1998-04-12'),
(8,'魏征','男',28,'洛ab阳',1,'1967-07-18'),
(9,'徐达','男',29,'江苏无锡',2,'1985-05-11'),
(12,'小虎','男',21,'上海',2,'2006-07-19');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(32) NOT NULL COMMENT '资源类型：menu,button,',
  `url` varchar(128) DEFAULT NULL COMMENT '访问url地址',
  `percode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父结点id',
  `parentids` varchar(128) DEFAULT NULL COMMENT '父结点id列表串',
  `sortstring` varchar(128) DEFAULT NULL COMMENT '排序号',
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`name`,`type`,`url`,`percode`,`parentid`,`parentids`,`sortstring`,`available`) values 
(1,'权限','','',NULL,0,'0/','0','1'),
(11,'学生管理','menu','/student/tolist','student:tolist',1,'0/1/','1.','1'),
(12,'学生新增','permission','/student/add.do','student:create',11,'0/1/11/','','1'),
(13,'学生修改','permission','/student/update.do','student:update',11,'0/1/11/','','1'),
(14,'学生删除','permission','/student/delete.do','student:delete',11,'0/1/11/','','1'),
(15,'学生查询','permission','/student/list','stuent:list',11,'0/1/15/',NULL,'1'),
(21,'用户管理','menu','/user/userlist','user:userlist',1,'0/1/','2.','1'),
(22,'用户新增','permission','bbb','user:create',21,'0/1/21/','','1'),
(23,'用户修改','permission','cccc ','user:update',21,'0/1/21/','','1'),
(24,'用户删除','permission','ddd','user:delete',21,'0/1/21/','','1'),
(25,'到学生列表','permission','/student/tolist','student:tolist',11,'0/1/11',NULL,'1'),
(26,'分页学生列表','permission','/student/search.do','student:search',11,'0/1/11',NULL,'1'),
(27,'分配角色','menu','/user/changerole.html','user:touserlist',1,'0/1/',NULL,'1'),
(28,'分配权限','menu','/role/permissionlist.do','role:permissionlist',1,'0/1',NULL,'1'),
(29,'修改用户角色权限','permission','/role/changeRole','user:roleUpdate',27,'0/1/27',NULL,'1'),
(30,'修改角色下的权限','permission','/role/permissionUpdate.do','role:permissionUpdate',28,'0/1/28',NULL,'1'),
(31,'aaa','permission','/user/userlist','user:userlist',27,'0/1/27',NULL,'1'),
(32,'bbb','permission','/role/topermissionlist.do','role:topermissionlist',28,'0/1/28',NULL,'1'),
(33,'ccc','permission','/user/touserlist','user:touserlist',27,'0/1/27',NULL,'1'),
(34,'ddd','permission','/role/rolelist.do','role:rolelist.action',27,'0/1/27',NULL,'1'),
(35,'eee','permission','/user/rolelist.do','user:rolelist.action',27,'0/1/27',NULL,'1'),
(36,'fff','permission','/userrole/update.do','userrole:update.action',27,'0/1/27',NULL,'1'),
(37,'gggg','permission','/user/listmenu.do','user:listmenu.do',27,'0/1/27',NULL,'1'),
(38,'hhhh','permission','/user/changerole.html','user:touserlist.do',27,'0/1/27',NULL,'1'),
(39,'jjjj','permission','/role/getName','role:getName.do',27,'0/1/27',NULL,'1');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(128) NOT NULL,
  `available` char(1) DEFAULT NULL COMMENT '是否可用,1：可用，0不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`available`) values 
('ebc8a441-c6f9-11e4-b137-0adc305c3f28','商品管理员','1'),
('ebc8a441-c6f9-11e4-b137-0adc305c3f29','超级管理员','1'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f28','用户管理员','1');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` varchar(36) NOT NULL,
  `sys_role_id` varchar(36) NOT NULL COMMENT '角色id',
  `sys_permission_id` varchar(36) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`sys_role_id`,`sys_permission_id`) values 
('ebc8a441-c6f9-11e4-b137-0adc305c3f21','ebc8a441-c6f9-11e4-b137-0adc305c3f28','12'),
('ebc8a441-c6f9-11e4-b137-0adc305c3f22','ebc8a441-c6f9-11e4-b137-0adc305c3f28','11'),
('ebc8a441-c6f9-11e4-b137-0adc305c3f24','ebc9d647-c6f9-11e4-b137-0adc305c3f28','21'),
('ebc8a441-c6f9-11e4-b137-0adc305c3f25','ebc8a441-c6f9-11e4-b137-0adc305c3f28','15'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f23','ebc9d647-c6f9-11e4-b137-0adc305c3f28','22'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f26','ebc8a441-c6f9-11e4-b137-0adc305c3f28','13'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f27','ebc8a441-c6f9-11e4-b137-0adc305c3f28','25'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f28','ebc8a441-c6f9-11e4-b137-0adc305c3f28','26'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f30','ebc8a441-c6f9-11e4-b137-0adc305c3f28','31'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f31','ebc8a441-c6f9-11e4-b137-0adc305c3f28','32'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f32','ebc8a441-c6f9-11e4-b137-0adc305c3f28','29'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f33','ebc8a441-c6f9-11e4-b137-0adc305c3f28','30'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f34','ebc8a441-c6f9-11e4-b137-0adc305c3f28','33'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f35','ebc8a441-c6f9-11e4-b137-0adc305c3f28','34'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f36','ebc8a441-c6f9-11e4-b137-0adc305c3f28','35'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f37','ebc8a441-c6f9-11e4-b137-0adc305c3f28','36'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f50','ebc8a441-c6f9-11e4-b137-0adc305c3f29','28'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f59','ebc8a441-c6f9-11e4-b137-0adc305c3f29','27'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f60','ebc8a441-c6f9-11e4-b137-0adc305c3f28','37'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f61','ebc8a441-c6f9-11e4-b137-0adc305c3f28','38'),
('ebc9d647-c6f9-11e4-b137-0adc305c3f62','ebc8a441-c6f9-11e4-b137-0adc305c3f28','39');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `usercode` varchar(32) NOT NULL COMMENT '账号',
  `username` varchar(64) NOT NULL COMMENT '姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `locked` char(1) DEFAULT NULL COMMENT '账号是否锁定，1：锁定，0未锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`usercode`,`username`,`password`,`salt`,`locked`) values 
('lisi','lisi','李四','ec1b86316c81b3f3440c07f65a74bf79','rbtwy','0'),
('zhangsan','zhangsan','张三','ec1b86316c81b3f3440c07f65a74bf79','rbtwy','0');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(36) NOT NULL,
  `sys_user_id` varchar(36) NOT NULL,
  `sys_role_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`sys_user_id`,`sys_role_id`) values 
('38b17f29-8f36-4d48-8c2f-efc31993d870','lisi','ebc8a441-c6f9-11e4-b137-0adc305c3f29'),
('53205612-0944-4557-9102-68d806040458','zhangsan','ebc9d647-c6f9-11e4-b137-0adc305c3f28'),
('65945692-7144-40b6-a6a7-faf3acab1de4','lisi','ebc8a441-c6f9-11e4-b137-0adc305c3f28'),
('e2ffb204-b59c-4540-9451-e0b925b540f2','lisi','ebc9d647-c6f9-11e4-b137-0adc305c3f28'),
('e3684f1f-3d2f-43c1-b9b1-0ebb04057c96','zhangsan','ebc8a441-c6f9-11e4-b137-0adc305c3f28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
