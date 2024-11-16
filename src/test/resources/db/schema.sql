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