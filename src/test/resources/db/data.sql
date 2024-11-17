INSERT INTO `question` (`id`, `user_id`, `title`, `content`, `published_at`)
VALUES (1, 1, 'question title', 'question content', '2024-11-13 22:38:12');

INSERT INTO `answer` (`id`, `question_id`, `user_id`, `content`)
VALUES (1, 1, 1, '22');

INSERT INTO `user` (`id`, `name`, `phone`, `email`, `password`)
VALUES (1, 'user', '18812345678', 'email@qq.com', 'password');
INSERT INTO `user` (`id`, `name`, `phone`, `email`, `password`)
VALUES (2, 'another_user', '18812345678', 'email@qq.com', 'password');