CREATE DATABASE if not EXISTS note;

CREATE TABLE user(
                     id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                     user_name varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL UNIQUE COMMENT '用户名',
                     password varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '密码',
                     validity varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '有效' COMMENT '用户是			 否可用'
);

CREATE TABLE admin(
                      id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                      admin_name varchar(32) CHARACTER SET utf8 COLLATE
                          utf8_general_ci NOT NULL UNIQUE COMMENT '用户名',
                      password varchar(32) CHARACTER SET utf8 COLLATE
                          utf8_general_ci NOT NULL COMMENT '密码'
);

CREATE TABLE announcement(
                             id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                             title varchar(32) CHARACTER SET utf8 COLLATE
                                 utf8_general_ci NOT NULL UNIQUE COMMENT '标题',
                             text varchar(1000) CHARACTER SET utf8 COLLATE
                                 utf8_general_ci NOT NULL COMMENT '公告内容'
);

CREATE TABLE note(
                     id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                     title varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL  COMMENT '标题',
                     author_id varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '作者',
                     text varchar(1000) CHARACTER SET utf8 COLLATE
                         utf8_general_ci NOT NULL COMMENT '笔记内容',
                     access varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci DEFAULT '公开' COMMENT '权限',
                     like_count INT COMMENT '点赞数',
                     located_group varchar(32) CHARACTER SET utf8 COLLATE
                         utf8_general_ci DEFAULT(NULL) COMMENT '所在分组'
);
CREATE TABLE located_group(
                              id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                              group_name varchar(32) CHARACTER SET utf8 COLLATE
                                  utf8_general_ci NOT NULL  COMMENT '名称',
                              author_id varchar(32) CHARACTER SET utf8 COLLATE
                                  utf8_general_ci NOT NULL COMMENT '作者',
                              located_folder varchar(32) CHARACTER SET utf8 COLLATE
                                  utf8_general_ci DEFAULT(NULL) COMMENT '所在知识库'
);
CREATE TABLE located_folder(
                               id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                               folder_name varchar(32) CHARACTER SET utf8 COLLATE
                                   utf8_general_ci NOT NULL  COMMENT '知识库名称',
                               author_id varchar(32) CHARACTER SET utf8 COLLATE
                                   utf8_general_ci NOT NULL COMMENT '作者',
                               access varchar(32) CHARACTER SET utf8 COLLATE
                                   utf8_general_ci DEFAULT '公开' COMMENT '权限'
);

CREATE TABLE like_list(
                          id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                          note_id varchar(32) CHARACTER SET utf8 COLLATE
                              utf8_general_ci NOT NULL COMMENT '对应笔记',
                          user_id varchar(32) CHARACTER SET utf8 COLLATE
                              utf8_general_ci NOT NULL COMMENT '对应用户'
);
CREATE TABLE forum(
                      id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
                      title varchar(32) CHARACTER SET utf8 COLLATE
                          utf8_general_ci NOT NULL  COMMENT '标题',
                      text varchar(1000) CHARACTER SET utf8 COLLATE
                          utf8_general_ci NOT NULL COMMENT '论坛内容',
                      user_id varchar(32) CHARACTER SET utf8 COLLATE
                          utf8_general_ci NOT NULL COMMENT '对应用户'
);




INSERT INTO `admin`(`id`, `admin_name`, `password`) VALUES (1, 'Melo', 'lRyl48IYkeiidUzsv54xEw==');

INSERT INTO `announcement`(`id`, `title`, `text`) VALUES (1, '欢迎使用', '欢迎使用');
INSERT INTO `announcement`(`id`, `title`, `text`) VALUES (2, '今天到此结束', '谢谢');
INSERT INTO `announcement`(`id`, `title`, `text`) VALUES (3, '额', '额');
INSERT INTO `announcement`(`id`, `title`, `text`) VALUES (4, '好的', '好');
INSERT INTO `announcement`(`id`, `title`, `text`) VALUES (5, '今天', '今天');

INSERT INTO `forum`(`id`, `title`, `text`, `user_id`) VALUES (1, '不错', '软件挺不错的', '2');
INSERT INTO `forum`(`id`, `title`, `text`, `user_id`) VALUES (2, '楼上骗人的', '踩雷', '3');
INSERT INTO `forum`(`id`, `title`, `text`, `user_id`) VALUES (3, '不好', '不好', '5');
INSERT INTO `forum`(`id`, `title`, `text`, `user_id`) VALUES (4, '不错', '11', '7');

INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (1, '默认', '2', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (2, '默认', '3', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (4, '默认', '4', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (5, '娱乐', '4', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (6, '默认', '5', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (7, '小樱', '5', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (8, '默认', '7', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (10, '默认', '1', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (11, '学习', '1', '公开');
INSERT INTO `located_folder`(`id`, `folder_name`, `author_id`, `access`) VALUES (12, '娱乐', '1', '公开');

INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (1, '默认', '2', '1');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (2, '默认', '3', '2');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (3, '忍术打击', '3', '2');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (4, '默认', '4', '4');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (6, '默认', '5', '6');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (8, '好', '5', '7');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (9, '默认', '7', '8');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (13, '默认', '1', '10');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (14, '高数', '1', '11');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (15, '大英', '1', '11');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (16, '大物', '1', '11');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (17, 'LOL', '1', '12');
INSERT INTO `located_group`(`id`, `group_name`, `author_id`, `located_folder`) VALUES (18, 'NBA', '1', '12');

INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (2, '默认的', '4', '默认的', '公开', 0, '4');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (3, '火影忍者', '4', '十多年前一只恐怖的尾兽“九尾妖狐”袭击了木叶隐村，当时的第四代火影拼尽全力，以自己的生命为代价将“九尾妖狐”封印在了刚出生的鸣人身上。木叶村终于恢复了平静，但村民们却把鸣人当成怪物看待，所有人都疏远他。无可奈何，鸣人用各种恶作剧试图吸引大家的注意力。在伊鲁卡老师的关心下，鸣人始终保持着乐观的精神。为了让更多的人认可自己，他下定决心要成为火影！鸣人怀着过人的自信与勇气开始了训练，但一切要比他想象的要困难的多！', '公开', 0, '4');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (8, '头秃了', '1', '高数针不戳阿针不戳', '公开', 0, '14');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (9, '还不错', '1', '大英还行', '公开', 0, '15');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (10, '不太行', '1', '大物不行', '公开', 0, '16');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (11, 'LOL', '1', 'LOL!', '公开', 0, '17');
INSERT INTO `note`(`id`, `title`, `author_id`, `text`, `access`, `like_count`, `located_group`) VALUES (12, 'NBA', '1', 'This is why we play', '公开', 0, '18');

INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (1, '鸣人', 'xMpCOKC5I4INzFCab3WEmw==', '有效');
INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (2, '我爱罗', 'hlwMC0qw4GPlyqM4fBqHQQ==', '有效');
INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (3, '佐助sama', 'ttdn0vjtXSGkSw5YhmgMuQ==', '有效');
INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (4, '瓜哥', 'xMpCOKC5I4INzFCab3WEmw==', '有效');
INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (5, '小樱', 'ZRK9Q9nKpuAsmQsKgmUtyg==', '无效');
INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (6, '大蛇丸', 'xMpCOKC5I4INzFCab3WEmw==', '有效');
INSERT INTO `user`(`id`, `user_name`, `password`, `validity`) VALUES (7, 'sam', 'ZRK9Q9nKpuAsmQsKgmUtyg==', '有效');



