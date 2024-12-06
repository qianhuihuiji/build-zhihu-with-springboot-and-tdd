CREATE TABLE `question`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT,
    `user_id`      int          NOT NULL,
    `title`        varchar(100)     NOT NULL,
    `content`      text             NOT NULL,
    `published_at` timestamp        NULL     DEFAULT NULL,
    best_answer_id int           null comment '最佳答案',
    category_id    int         not null comment '分类编号',
    answers_count  int          not null default 0 comment '回答数量',
    `slug`        varchar(100)     NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;
