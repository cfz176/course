#-----------------------------------分类表
drop table if exists `category`;
create table category
(
    `id`     char(8)     not null default '' comment 'id',
    `parent` char(8)     not null default '' comment '上级id',
    `name`   varchar(50) not null default '' comment '名称',
    `sort`   int comment '排序',
    primary key (id)
)engine=innodb default charset=utf8mb4 comment='分类';

insert into `category` (`id`, `parent`, `name`, `sort`) values ('000100','000000','小学',100);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000101','000100','一年级',101);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000102','000100','二年级',102);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000103','000100','三年级',103);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000104','000100','四年级',104);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000105','000100','五年级',105);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000106','000100','六年级',106);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000200','000000','初中',200);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000201','000200','七年级',201);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000202','000200','八年级',202);
insert into `category` (`id`, `parent`, `name`, `sort`) values ('000203','000200','九年级',203);

#-----------------------------------课程表
drop table if exists `course`;
create table course(
    `id` char(8) not null default '' comment 'id',
    `name` varchar(50) not null comment '名称',
    `summary` varchar(2000) comment '概述',
    `time` int default 0 comment '时长|单位秒',
    `price` decimal(8,2) default 0.00 comment '价格',
    `image` varchar(200) comment '封面',
    `level` varchar (100) comment '级别|枚举[CourseLevelEnum]ONE("1","初级"）,TOW("2","中级"）,THREE("","高级"）',
    `charge` char(1) comment '收费|枚举[CourseChargeEnum]CHARGE("C","收费"),FREE("F","免费")',
    `status` char(1) comment '状态|枚举[CourseStatusEnum]PUBLISH("P","发布"),DRAF("D","草稿")',
    `enroll` integer default 0 comment '报名数',
    `sort` int comment '顺序',
    `create_at` datetime(3) comment '创建时间',
    `update_at` datetime(3) comment '修改时间',
    primary key(id)
)engine=innodb default charset=utf8mb4 comment='课程';

#-----------------------------------小节表
drop table if exists `section`;
create table `section`(
    `id` char (8) not null default '' comment 'ID',
    `title` varchar (50) not null comment '标题',
    `course_id` char (8) comment '课程|course_id',
    `chapter_id` char (8)  comment '章节|course_id',
    `video` varchar (200)  comment '视频',
    `time` int   comment '时长|单位秒',
    `charge` char(1)   comment '收费|C 收费；F 免费',
    `sort` int   comment '顺序',
    `created_at` Datetime comment '创建时间',
    `updated_at` Datetime   comment '修改时间',
    primary key(`id`)
)engine=innodb default charset=utf8mb4 comment='小节';
insert into `section`(id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
values ('000003', '小节1', '001', '002', ' ', 300, 'F', 1, now(), now());


#-----------------------------------章节表
drop table if exists `chapter`;
create table `chapter`(
 `id` char(8) not null comment 'ID',
 `course_id` char(8) comment '课程ID',
 `name` char(60) comment '名称',
 primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='大章';
insert into `chapter`(`id`,`course_id`,`name`) values('0001','8888','课程01');
insert into `chapter`(`id`,`course_id`,`name`) values('0002','8888','课程02');
insert into `chapter`(`id`,`course_id`,`name`) values('0003','8888','课程03');
insert into `chapter`(`id`,`course_id`,`name`) values('0004','8888','课程04');

#---------------------------------- 测试表
drop table if exists `test`;
create table `test`(
    `id` char(8) not null default '' comment 'id',
    `name` varchar(50) comment '名称',
    primary key(id)
)engine=innodb default charset=utf8mb4 comment='测试';
insert into test (id,name) values (1,'测试');
insert into test (id,name) values (2,'测试2');