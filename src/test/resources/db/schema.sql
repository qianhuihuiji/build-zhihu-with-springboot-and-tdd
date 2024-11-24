CREATE TABLE `answer` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                          `question_id` int(11) NOT NULL,
                          `user_id` int(11) NOT NULL,
                          `content` text  NOT NULL,
                          `created_at` timestamp NULL DEFAULT NULL,
                          `updated_at` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

CREATE TABLE `question` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                          `user_id` int(11) NOT NULL,
                          `title` varchar(100) NOT NULL ,
                          `content` text  NOT NULL,
                          `published_at` timestamp NULL DEFAULT NULL,
                          best_answer_id    bigint     null comment '最佳答案',
                          category_id    smallint     not null comment '分类编号',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

CREATE TABLE `user` (
                        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(100)  NOT NULL,
                        `phone` VARCHAR(100)  NOT NULL,
                        `email` VARCHAR(100)  NOT NULL,
                        `password` VARCHAR(100)  NOT NULL,
                        `created_at` timestamp NULL DEFAULT NULL,
                        `updated_at` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;


CREATE TABLE `vote` (
                        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `user_id` int(4) NOT NULL,
                         `voted_id` int(10) NOT NULL,
                         `resource_type` VARCHAR(10) NOT NULL,
                         `action_type` VARCHAR(10) NOT NULL,
                         `created_at` timestamp NOT NULL  ,
                         `updated_at` timestamp NOT NULL  ,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

ALTER TABLE `vote` add CONSTRAINT unq_user_id_vote UNIQUE (`user_id`, `voted_id`,`resource_type`,`action_type`);

create table `category`
(
    `id`   smallint auto_increment comment '主键'
        primary key,
    `name` varchar(10) not null comment '分类命',
    `slug` varchar(50) null comment '标签'
);

