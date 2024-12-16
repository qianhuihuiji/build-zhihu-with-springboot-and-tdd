CREATE TABLE `answer` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `question_id` int NOT NULL,
                          `user_id` int NOT NULL,
                          `content` text  NOT NULL,
                          `created_at` timestamp NOT NULL,
                          `updated_at` timestamp NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;
