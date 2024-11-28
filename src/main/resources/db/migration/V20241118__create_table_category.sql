create table `category`
(
    `id`   smallint auto_increment comment '主键'
        primary key,
    `name` varchar(10) not null comment '分类命',
    `slug` varchar(50) null comment '标签'
);

