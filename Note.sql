CREATE TABLE user(
                     id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                     user_name varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '用户名',
                     password varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '密码',
                     photo varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户头像'
);

CREATE TABLE note(
                     id varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci PRIMARY KEY  COMMENT '主键',
                     title varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '标题',
                     author_id varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '作者',
                     text varchar(255) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '笔记内容',
                     access varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci DEFAULT '私有' COMMENT '权限',
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
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (5, 'Very Good', '1', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (6, '好', '1', '公开');





INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (1, 'hh', '1', '4');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (8, '喂', '1', '4');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (11, 'LOL', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (12, '额', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (13, '非常好', '1', '5');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (14, '好的', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (15, '额额', '1', '3');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (16, '不好', '1', '6');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (17, '星星', '1', '3');



INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES ('1', '不是很好吧', '1', '不错', '私有', '0', '1');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES ('2', '2', '1', '2', '公开', '0', '16');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES ('3', '额', '1', '额', '公开', '0', '16');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES ('4', '2', '1', '2', '公开', '0', '13');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES ('5', '额', '1', '额额', '公开', '0', '16');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES ('6', '3', '1', '3', '公开', '0', '16');


INSERT INTO `user`(`id`, `user_name`, `password`, `photo`) VALUES (1, 'Naruto', 'tZxnvxlqR1gZHkL3ZnDOug==', '');
INSERT INTO `user`(`id`, `user_name`, `password`, `photo`) VALUES (2, '佐助', 'K+m9ejQ09wOMon0ZGN5YvQ==', '');

