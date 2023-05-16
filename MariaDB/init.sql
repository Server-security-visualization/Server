CREATE TABLE `packet_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`source_ip`	VARCHAR(15)	NOT NULL,
	`source_port`	INT	NOT NULL,
	`dest_ip`	VARCHAR(15)	NOT NULL,
	`dest_port`	INT	NOT NULL,
	`protocol`	DOUBLE	NOT NULL,
	`timestamp`	INT	NOT NULL,
	`flow_duration`	DOUBLE	NOT NULL,
	`total_fwd_packets`	DOUBLE	NOT NULL,
	`total_backward_packets`	DOUBLE	NOT NULL,
	`total_length_of_fwd_packets`	DOUBLE	NOT NULL,
	`total_length_of_bwd_packets`	DOUBLE	NOT NULL,
	`fwd_packet_length_max`	DOUBLE	NOT NULL,
	`fwd_packet_length_min`	DOUBLE	NOT NULL,
	`fwd_packet_length_mean`	DOUBLE	NOT NULL,
	`fwd_packet_length_std`	DOUBLE	NOT NULL,
	`bwd_packet_length_max`	DOUBLE	NOT NULL,
	`bwd_packet_length_min`	DOUBLE	NOT NULL,
	`bwd_packet_length_mean`	DOUBLE	NOT NULL,
	`bwd_packet_length_std`	DOUBLE	NOT NULL,
	`flow_bytes`	DOUBLE	NOT NULL,
	`flow_packets`	DOUBLE	NOT NULL,
	`flow_iat_mean`	DOUBLE	NOT NULL,
	`flow_iat_std`	DOUBLE	NOT NULL,
	`flow_iat_max`	DOUBLE	NOT NULL,
	`flow_iat_min`	DOUBLE	NOT NULL,
	`fwd_iat_total`	DOUBLE	NOT NULL,
	`fwd_iat_mean`	DOUBLE	NOT NULL,
	`fwd_iat_std`	DOUBLE	NOT NULL,
	`fwd_iat_max`	DOUBLE	NOT NULL,
	`fwd_iat_min`	DOUBLE	NOT NULL,
	`bwd_iat_total`	DOUBLE	NOT NULL,
	`bwd_iat_mean`	DOUBLE	NOT NULL,
	`bwd_iat_std`	DOUBLE	NOT NULL,
	`bwd_iat_max`	DOUBLE	NOT NULL,
	`bwd_iat_min`	DOUBLE	NOT NULL,
	`fwd_psh_flags`	DOUBLE	NOT NULL,
	`bwd_psh_flags`	DOUBLE	NOT NULL,
	`fwd_urg_flags`	DOUBLE	NOT NULL,
	`bwd_urg_flags`	DOUBLE	NOT NULL,
	`fwd_header_length`	DOUBLE	NOT NULL,
	`bwd_header_length`	DOUBLE	NOT NULL,
	`fwd_packets`	DOUBLE	NOT NULL,
	`bwd_packets`	DOUBLE	NOT NULL,
	`min_packet_length`	DOUBLE	NOT NULL,
	`max_packet_length`	DOUBLE	NOT NULL,
	`packet_length_mean`	DOUBLE	NOT NULL,
	`packet_length_std`	DOUBLE	NOT NULL,
	`packet_length_variance`	DOUBLE	NOT NULL,
	`fin_flag_count`	DOUBLE	NOT NULL,
	`syn_flag_count`	DOUBLE	NOT NULL,
	`rst_flag_count`	DOUBLE	NOT NULL,
	`psh_flag_count`	DOUBLE	NOT NULL,
	`ack_flag_count`	DOUBLE	NOT NULL,
	`urg_flag_count`	DOUBLE	NOT NULL,
	`cwe_flag_count`	DOUBLE	NOT NULL,
	`ece_flag_count`	DOUBLE	NOT NULL,
	`down_up_ratio`	DOUBLE	NOT NULL,
	`average_packet_size`	DOUBLE	NOT NULL,
	`avg_fwd_segment_size`	DOUBLE	NOT NULL,
	`avg_bwd_segment_size`	DOUBLE	NOT NULL,
	`fwd_avg_bytes_bulk`	DOUBLE	NOT NULL,
	`fwd_avg_packets_bulk`	DOUBLE	NOT NULL,
	`fwd_avg_bulk_rate`	DOUBLE	NOT NULL,
	`bwd_avg_bytes_bulk`	DOUBLE	NOT NULL,
	`bwd_avg_packets_bulk`	DOUBLE	NOT NULL,
	`bwd_avg_bulk_rate`	DOUBLE	NOT NULL,
	`subflow_fwd_packets`	DOUBLE	NOT NULL,
	`subflow_fwd_bytes`	DOUBLE	NOT NULL,
	`subflow_bwd_packets`	DOUBLE	NOT NULL,
	`subflow_bwd_bytes`	DOUBLE	NOT NULL,
	`init_win_bytes_forward`	DOUBLE	NOT NULL,
	`init_win_bytes_backward`	DOUBLE	NOT NULL,
	`act_data_pkt_fwd`	DOUBLE	NOT NULL,
	`min_seg_size_forward`	DOUBLE	NOT NULL,
	`active_mean`	DOUBLE	NOT NULL,
	`active_std`	DOUBLE	NOT NULL,
	`active_max`	DOUBLE	NOT NULL,
	`active_min`	DOUBLE	NOT NULL,
	`idle_mean`	DOUBLE	NOT NULL,
	`idle_std`	DOUBLE	NOT NULL,
	`idle_max`	DOUBLE	NOT NULL,
	`idle_min`	DOUBLE	NOT NULL
);

CREATE TABLE `file_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`ip`	VARCHAR(15)	NOT NULL,
	`file`	MEDIUMBLOB	NOT NULL,
	`timestamp`	DATETIME	NOT NULL
);

CREATE TABLE `block_user_table` (
	`ip`	VARCHAR(15)	NOT NULL,
	`time`	DATETIME	NOT NULL,
	`reason`	VARCHAR(15)	NOT NULL
);

CREATE TABLE `packet_detection_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`detection`	VARCHAR(100)	NOT NULL,
	`type`	VARCHAR(20)	NOT NULL,
	`packet_idx`	INT	NOT NULL
);

CREATE TABLE `file_detection_table` (
	`idx`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`img`	MEDIUMBLOB	NOT NULL,
	`detection`	VARCHAR(100)	NOT NULL,
	`type`	VARCHAR(20)	NOT NULL,
	`file_idx`	INT	NOT NULL
);

CREATE TABLE `auth_table` (
	`uuid`	VARCHAR(36)	NOT NULL
);

CREATE TABLE `server_status_table` (
	`time`	TIMESTAMP	NOT NULL,
	`cpu`	INT	NOT NULL,
	`ram`	INT	NOT NULL,
	`network`	INT	NOT NULL
);

ALTER TABLE `block_user_table` ADD CONSTRAINT `PK_BLOCK_USER_TABLE` PRIMARY KEY (
	`ip`
);

ALTER TABLE `auth_table` ADD CONSTRAINT `PK_AUTH_TABLE` PRIMARY KEY (
	`uuid`
);

ALTER TABLE `server_status_table` ADD CONSTRAINT `PK_SERVER_STATUS_TABLE` PRIMARY KEY (
	`time`
);

ALTER TABLE `packet_detection_table` ADD CONSTRAINT `FK_packet_table_TO_packet_detection_table_1` FOREIGN KEY (
	`packet_idx`
)
REFERENCES `packet_table` (
	`idx`
);

ALTER TABLE `file_detection_table` ADD CONSTRAINT `FK_file_table_TO_file_detection_table_1` FOREIGN KEY (
	`file_idx`
)
REFERENCES `file_table` (
	`idx`
);

INSERT INTO auth_table VALUES (UUID());