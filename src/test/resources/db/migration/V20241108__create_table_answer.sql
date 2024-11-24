CREATE TABLE `answer` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                          `question_id` int(11) NOT NULL,
                          `user_id` int(11) NOT NULL,
                          `content` text  NOT NULL,
                          `created_at` timestamp NULL DEFAULT NULL,
                          `updated_at` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;
