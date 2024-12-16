CREATE TABLE `activity`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT,
    `user_id`      int          NOT NULL,
    `type`         VARCHAR(50)  NOT NULL,
    `subject_id`   int          NOT NULL,
    `subject_type` VARCHAR(50)  NOT NULL,
    `created_at`   timestamp    NULL DEFAULT NULL,
    `updated_at`   timestamp    NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;
