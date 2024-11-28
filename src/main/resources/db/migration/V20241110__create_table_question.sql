
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
