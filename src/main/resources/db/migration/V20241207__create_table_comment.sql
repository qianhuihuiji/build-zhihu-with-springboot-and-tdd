CREATE TABLE `comment` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `user_id` int NOT NULL,
                          `content` text  NOT NULL,
                          `commented_id` int NOT NULL,
                          `commented_type` VARCHAR(10) NOT NULL,
                          `created_at` timestamp NULL DEFAULT NULL,
                          `updated_at` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;
