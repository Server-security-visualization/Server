CREATE TABLE `web_log_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`direction`	INT	NOT NULL,
	`http_method`	VARCHAR(10)	NOT NULL,
	`http_query`	VARCHAR(500)	NOT NULL,
	`http_version`	VARCHAR(10)	NOT NULL,
	`http_url`	VARCHAR(500)	NOT NULL,
	`http_status`	INT	NOT NULL,
	`pkt_bytes`	INT	NOT NULL,
	`rcvd_bytes`	INT	NOT NULL,
	`sent_bytes`	INT	NOT NULL,
	`referer`	VARCHAR(500)	NOT NULL
);

CREATE TABLE `web_log_detection_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`detection`	DOUBLE	NOT NULL,
	`web_log_idx`	INT	NOT NULL
);

CREATE TABLE `file_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`ip`	VARCHAR(15)	NOT NULL,
	`file`	MEDIUMBLOB	NOT NULL,
	`filename`	VARCHAR(100)	NOT NULL,
	`timestamp`	DATETIME	NOT NULL
);

CREATE TABLE `file_detection_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`img`	MEDIUMBLOB	NOT NULL,
	`detection`	DOUBLE	NOT NULL,
	`type`	VARCHAR(20)	NOT NULL,
	`file_idx`	INT	NOT NULL
);

CREATE TABLE `block_user_table` (
	`ip`	VARCHAR(15)	NOT NULL,
	`time`	DATETIME	NOT NULL,
	`reason`	VARCHAR(15)	NOT NULL
);

CREATE TABLE `auth_table` (
	`uuid`	VARCHAR(36)	NOT NULL
);

ALTER TABLE `block_user_table` ADD CONSTRAINT `PK_BLOCK_USER_TABLE` PRIMARY KEY (
	`ip`
);

ALTER TABLE `auth_table` ADD CONSTRAINT `PK_AUTH_TABLE` PRIMARY KEY (
	`uuid`
);

ALTER TABLE `file_detection_table` ADD CONSTRAINT `FK_file_table_TO_file_detection_table_1` FOREIGN KEY (
	`file_idx`
)
REFERENCES `file_table` (
	`idx`
);

ALTER TABLE `web_log_detection_table` ADD CONSTRAINT `FK_web_log_table_TO_web_log_detection_table_1` FOREIGN KEY (
	`web_log_idx`
)
REFERENCES `web_log_table` (
	`idx`
);

INSERT INTO auth_table VALUES (UUID());