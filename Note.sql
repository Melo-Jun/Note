CREATE TABLE user(
			 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
			 user_name varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '用户名',
			 password varchar(32) CHARACTER SET utf8 COLLATE 
			 utf8_general_ci NOT NULL COMMENT '密码',
			 photo varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户头像'
			 );
			 
CREATE TABLE note(
			 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
			 title varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '标题',
			 author_id varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '作者',
			 text varchar(255) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '笔记内容',
			 access varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci DEFAULT '私有' COMMENT '权限',
			 gmt_create datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0)
			 COMMENT 	'创建时间',
			 gmt_modified datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) 
			 ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
			 like_count varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci DEFAULT '0' COMMENT '点赞数',
			 located_group varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci DEFAULT(NULL) COMMENT '所在分组'
			 );
CREATE TABLE located_group(
			 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
			 group_name varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '名称',
			 author_id varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '作者',
			 located_folder varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci DEFAULT(NULL) COMMENT '所在知识库'
			 );
CREATE TABLE located_folder(
			 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
			 folder_name varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '知识库名称',
			 author_id varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci NOT NULL COMMENT '作者',
			 access varchar(32) CHARACTER SET utf8 COLLATE
			 utf8_general_ci DEFAULT '私有' COMMENT '权限'
			 );



INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (3, '开黑', '1', '私有');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (4, '学习', '1', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (5, '好', '1', '公开');


INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (1, 'hh', '1', '4');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (3, 'l', '1', '4');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (8, 'LOL', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (11, 'LOL啊', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (12, '额', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (13, '好', '1', '4');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (14, '好的', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (15, '额额', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (16, '好', '1', '5');


INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (3, '愚', '1', '想p吃', '公开', '2021-04-01 20:19:35', '2021-04-04 20:24:12', '0', '1');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (4, '人', '1', '额', '公开', '2021-04-01 20:22:04', '2021-04-04 20:16:04', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (5, '节', '1', '喂', '公开', '2021-04-01 20:26:31', '2021-04-04 20:16:10', '0', '1');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (6, '快', '1', '喂喂喂看什么呢', '公开', '2021-04-03 21:53:43', '2021-04-04 20:24:03', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (10, '乐', '1', '鹅鹅鹅起飞', '公开', '2021-04-03 21:59:07', '2021-04-04 20:24:06', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (11, '呀', '1', '不错哦', '私有', '2021-04-04 09:44:05', '2021-04-04 20:16:20', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (12, '!', '1', '飞飞', '公开', '2021-04-04 17:03:31', '2021-04-04 20:16:25', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (13, '啊', '2', '不知道该写点什么', '公开', '2021-04-04 20:20:43', '2021-04-04 20:20:43', '0', '');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (14, '这', '2', '同上', '公开', '2021-04-04 20:21:10', '2021-04-04 20:21:10', '0', NULL);
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (15, '! ', '2', '!', '私有', '2021-04-04 20:21:20', '2021-04-04 20:21:20', '0', NULL);
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (16, '你看不到我的', '1', '看不到我', '私有', '2021-04-04 20:26:43', '2021-04-04 20:26:43', '0', '1');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (17, '你看得到我了', '1', '看得到我', '公开', '2021-04-04 20:27:06', '2021-04-04 20:27:06', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (18, '你好', '1', '你还', '公开', '2021-04-04 21:22:16', '2021-04-04 21:22:16', '0', '2');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `gmt_create`, `gmt_modified`, `like_count`, `located_group`) VALUES (19, '嗯嗯', '1', '额鹅鹅鹅', '公开', '2021-04-05 09:17:39', '2021-04-05 09:17:39', '0', '1');


INSERT INTO `user`(`id`, `user_name`, `password`, `photo`) VALUES (1, 'Naruto', 'tZxnvxlqR1gZHkL3ZnDOug==', '');
INSERT INTO `user`(`id`, `user_name`, `password`, `photo`) VALUES (2, '佐助', 'K+m9ejQ09wOMon0ZGN5YvQ==', '');
