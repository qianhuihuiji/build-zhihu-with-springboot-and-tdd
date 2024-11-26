create table `notification`
(
    `id` int(10) unsigned auto_increment comment '主键'
        primary key,
    `type` varchar(255) not null comment '通知类型',
    `notifiable_id` int(10)  NOT NULL comment '接收者的id，如：user的id',
    `notifiable_type` varchar(255) not null comment '接收者的类名，如：User',
    `data` text not null comment '通知内容',
    `read_at` timestamp NULL DEFAULT NULL,
    `created_at` timestamp NULL DEFAULT NULL,
    `updated_at` timestamp NULL DEFAULT NULL
);

