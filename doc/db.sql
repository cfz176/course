drop table if exists `chapter`
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
insert into `chapter`(`id`,`course_id`,`name`) values('0005','8888','课程05');
insert into `chapter`(`id`,`course_id`,`name`) values('0006','8888','课程06');
insert into `chapter`(`id`,`course_id`,`name`) values('0007','8888','课程07');
insert into `chapter`(`id`,`course_id`,`name`) values('0008','8888','课程08');
insert into `chapter`(`id`,`course_id`,`name`) values('0009','8888','课程09');
insert into `chapter`(`id`,`course_id`,`name`) values('0010','8888','课程10');
insert into `chapter`(`id`,`course_id`,`name`) values('0011','8888','课程11');
insert into `chapter`(`id`,`course_id`,`name`) values('0012','8888','课程12');
insert into `chapter`(`id`,`course_id`,`name`) values('0013','8888','课程13');
insert into `chapter`(`id`,`course_id`,`name`) values('0014','8888','课程14');
insert into `chapter`(`id`,`course_id`,`name`) values('0015','8888','课程15');
insert into `chapter`(`id`,`course_id`,`name`) values('0016','8888','课程16');
insert into `chapter`(`id`,`course_id`,`name`) values('0017','8888','课程17');
insert into `chapter`(`id`,`course_id`,`name`) values('0018','8888','课程18');
insert into `chapter`(`id`,`course_id`,`name`) values('0019','8888','课程19');
insert into `chapter`(`id`,`course_id`,`name`) values('0020','8888','课程20');
insert into `chapter`(`id`,`course_id`,`name`) values('0021','8888','课程21');
insert into `chapter`(`id`,`course_id`,`name`) values('0022','8888','课程22');
insert into `chapter`(`id`,`course_id`,`name`) values('0023','8888','课程23');
insert into `chapter`(`id`,`course_id`,`name`) values('0024','8888','课程24');
insert into `chapter`(`id`,`course_id`,`name`) values('0025','8888','课程25');
insert into `chapter`(`id`,`course_id`,`name`) values('0026','8888','课程26');
insert into `chapter`(`id`,`course_id`,`name`) values('0027','8888','课程27');
insert into `chapter`(`id`,`course_id`,`name`) values('0028','8888','课程28');
insert into `chapter`(`id`,`course_id`,`name`) values('0029','8888','课程29');
insert into `chapter`(`id`,`course_id`,`name`) values('0030','8888','课程30');
insert into `chapter`(`id`,`course_id`,`name`) values('0031','8888','课程31');
insert into `chapter`(`id`,`course_id`,`name`) values('0032','8888','课程32');
insert into `chapter`(`id`,`course_id`,`name`) values('0033','8888','课程33');
insert into `chapter`(`id`,`course_id`,`name`) values('0034','8888','课程34');
insert into `chapter`(`id`,`course_id`,`name`) values('0035','8888','课程35');
insert into `chapter`(`id`,`course_id`,`name`) values('0036','8888','课程36');
insert into `chapter`(`id`,`course_id`,`name`) values('0037','8888','课程37');
insert into `chapter`(`id`,`course_id`,`name`) values('0038','8888','课程38');
insert into `chapter`(`id`,`course_id`,`name`) values('0039','8888','课程39');
insert into `chapter`(`id`,`course_id`,`name`) values('0040','8888','课程40');
insert into `chapter`(`id`,`course_id`,`name`) values('0041','8888','课程41');
insert into `chapter`(`id`,`course_id`,`name`) values('0042','8888','课程42');
insert into `chapter`(`id`,`course_id`,`name`) values('0043','8888','课程43');
insert into `chapter`(`id`,`course_id`,`name`) values('0044','8888','课程44');
insert into `chapter`(`id`,`course_id`,`name`) values('0045','8888','课程45');
insert into `chapter`(`id`,`course_id`,`name`) values('0046','8888','课程46');
insert into `chapter`(`id`,`course_id`,`name`) values('0047','8888','课程47');
insert into `chapter`(`id`,`course_id`,`name`) values('0048','8888','课程48');
insert into `chapter`(`id`,`course_id`,`name`) values('0049','8888','课程49');
insert into `chapter`(`id`,`course_id`,`name`) values('0050','8888','课程50');
insert into `chapter`(`id`,`course_id`,`name`) values('0051','8888','课程51');
insert into `chapter`(`id`,`course_id`,`name`) values('0052','8888','课程52');
insert into `chapter`(`id`,`course_id`,`name`) values('0053','8888','课程53');
insert into `chapter`(`id`,`course_id`,`name`) values('0054','8888','课程54');
insert into `chapter`(`id`,`course_id`,`name`) values('0055','8888','课程55');
insert into `chapter`(`id`,`course_id`,`name`) values('0056','8888','课程56');
insert into `chapter`(`id`,`course_id`,`name`) values('0057','8888','课程57');
insert into `chapter`(`id`,`course_id`,`name`) values('0058','8888','课程58');
insert into `chapter`(`id`,`course_id`,`name`) values('0059','8888','课程59');
insert into `chapter`(`id`,`course_id`,`name`) values('0060','8888','课程60');

#---------------------------------- 测试
drop table if exists `test`;
create table `test`(
    id char(8) not null default '' comment 'id',
    name varchar(50) comment '名称',
    primary key(id)
)engine=innodb default charset=utf8mb4 comment='测试';
insert into test (id,name) values (1,'测试');
insert into test (id,name) values (2,'测试2');