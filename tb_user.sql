drop database endwork;
Create database endwork;
use endwork;
drop table user;
create table `user`(
	uid varchar(32) DEFAULT NULL,
	username varchar(32) DEFAULT NULL,
	`password` varchar(64) DEFAULT NULL,
	`name` varchar(32) DEFAULT NULL,
	sex varchar(32) DEFAULT NULL,
	identity varchar(64) DEFAULT NULL,
	school varchar(64) DEFAULT NULL,
	telephone varchar(64) DEFAULT NULL,
	email varchar(64) DEFAULT NULL,
	`state` INT(11) DEFAULT NULL,
	`code` VARCHAR(64) DEFAULT NULL,
	state1 varchar(64) DEFAULT NULL,
	state2 varchar(64) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;
ALTER TABLE USER MODIFY uid VARCHAR(64);

		���û�Ϊ����Ա��ָ���˺ţ�ʱ����ʾ��ҳ��ѯ�����û�
	6���ϴ��û���ͷ��
		


		
	