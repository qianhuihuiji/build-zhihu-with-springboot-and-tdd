INSERT INTO `question` (`id`, `user_id`, `title`, `content`, `published_at`, `category_id`)
VALUES (1, 1, 'question title', 'question content', '2024-11-13 22:38:12', 1);

INSERT INTO `answer` (`id`, `question_id`, `user_id`, `content`)
VALUES (1, 1, 1, '22');

INSERT INTO `user` (`id`, `name`, `phone`, `email`, `password`)
VALUES (1, 'user', '18812345678', 'email@qq.com', 'password');
INSERT INTO `user` (`id`, `name`, `phone`, `email`, `password`)
VALUES (2, 'another_user', '18812345678', 'email@qq.com', 'password');

INSERT INTO `category` (`id`, `name`, `slug`)
VALUES (1, '经济', 'economy');

INSERT INTO category (id, name, slug)
VALUES (2, '科学', 'science');