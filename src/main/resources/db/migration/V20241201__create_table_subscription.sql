create table subscription
(
    id          int auto_increment comment '主键'
        primary key,
    user_id     int       not null comment '用户编号',
    question_id int       not null comment '问题编号',
    create_time timestamp not null comment '创建时间',
    update_time timestamp not null comment '更新时间',
    constraint unq_user_question
        unique (user_id, question_id)
)
    comment '问题订阅表';

