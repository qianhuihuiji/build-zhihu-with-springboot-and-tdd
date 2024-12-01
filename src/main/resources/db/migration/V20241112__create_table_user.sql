
CREATE TABLE `user` (
                        `id` int unsigned NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(100)  NOT NULL,
                        `phone` VARCHAR(100)  NOT NULL,
                        `email` VARCHAR(100)  NOT NULL,
                        `password` VARCHAR(100)  NOT NULL,
                        `created_at` timestamp NULL DEFAULT NULL,
                        `updated_at` timestamp NULL DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

