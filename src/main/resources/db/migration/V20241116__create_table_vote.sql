

CREATE TABLE `vote` (
                        `id` int unsigned NOT NULL AUTO_INCREMENT,
                        `user_id` int NOT NULL,
                        `voted_id` int NOT NULL,
                        `resource_type` VARCHAR(10) NOT NULL,
                        `action_type` VARCHAR(10) NOT NULL,
                        `created_at` timestamp NOT NULL  ,
                        `updated_at` timestamp NOT NULL  ,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

ALTER TABLE `vote` add CONSTRAINT unq_user_id_vote UNIQUE (`user_id`, `voted_id`,`resource_type`,`action_type`);



