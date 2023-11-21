/*
 Navicat Premium Data Transfer

 Source Server         : @192.168.100.92
 Source Server Type    : MySQL
 Source Server Version : 80018 (8.0.18)
 Source Host           : 192.168.100.92:13306
 Source Schema         : db_meta

 Target Server Type    : MySQL
 Target Server Version : 80018 (8.0.18)
 File Encoding         : 65001

 Date: 11/11/2023 20:10:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段 1 为删除',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父评论id',
  `user_id` int(11) NOT NULL COMMENT '评论用户id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `post_id` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '帖子id',
  `reply_user_id` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '回复用户id',
  `type` tinyint(4) NULL DEFAULT 0 COMMENT '评论类型 0 为评论 1 回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 'dsklajdjasdjsaljdlasjdlkasjlkdjlskadklaslkdjalk', 0, NULL, 1, '2023-10-31 17:40:05', '58', '1', 0);
INSERT INTO `t_comment` VALUES (2, 'dsklajdjasdjsaljdlasjdlkasjlkdjlskadklaslkdjalk20930-128930123781029379127312678387912893', 0, NULL, 1, '2023-10-31 17:41:39', '58', '1', 0);
INSERT INTO `t_comment` VALUES (3, 'dsklajdjasdjsaljdlasjdlkasjlkdjlskadklaslkdjalk20930-128930123781029379127312678387912893', 0, NULL, 1, '2023-10-31 17:42:07', '58', '1', 0);
INSERT INTO `t_comment` VALUES (4, 'dsklajdjasdjsaljdlasjd390213127319lkasjlkdjlskadklaslkdjalk', 0, NULL, 1, '2023-10-31 18:01:07', '58', '1', 0);
INSERT INTO `t_comment` VALUES (5, 'dolore esse sunt', 0, 1, 1, '2023-10-31 20:13:17', '58', '1', 1);
INSERT INTO `t_comment` VALUES (6, 'dolore esse sunt', 0, 1, 1, '2023-10-31 23:49:54', '58', '1', 1);
INSERT INTO `t_comment` VALUES (7, 'dolore esse sunt', 0, 1, 1, '2023-10-31 23:49:56', '58', '1', 1);
INSERT INTO `t_comment` VALUES (8, 'dolore esse sunt', 0, 1, 1, '2023-10-31 23:50:00', '58', '1', 1);
INSERT INTO `t_comment` VALUES (9, 'dolore esse sunt', 0, 1, 1, '2023-10-31 23:50:03', '58', '1', 1);
INSERT INTO `t_comment` VALUES (10, '0000000000', 0, NULL, 1, '2023-11-01 00:36:27', '58', '1', 0);
INSERT INTO `t_comment` VALUES (11, 'string', 0, 10, 1, '2023-11-01 15:42:19', '58', '1', 0);
INSERT INTO `t_comment` VALUES (12, 'string', 0, 10, 1, '2023-11-01 15:42:27', '58', '1', 0);
INSERT INTO `t_comment` VALUES (13, 'string', 0, 10, 1, '2023-11-01 15:44:42', '58', '1', 0);
INSERT INTO `t_comment` VALUES (14, 'string', 0, 10, 1, '2023-11-01 15:47:50', '58', '1', 0);
INSERT INTO `t_comment` VALUES (15, '你好啊', 0, NULL, 1, '2023-11-01 16:04:45', '58', '1', 0);
INSERT INTO `t_comment` VALUES (16, '很黑很黑', 0, NULL, 1, '2023-11-01 16:05:22', '58', '1', 0);
INSERT INTO `t_comment` VALUES (17, 'string', 0, 16, 1, '2023-11-01 16:07:29', '58', '1', 0);
INSERT INTO `t_comment` VALUES (18, 'string', 0, 16, 1, '2023-11-01 16:11:41', '58', '1', 0);
INSERT INTO `t_comment` VALUES (19, 'string', 0, 16, 1, '2023-11-01 16:20:58', '58', '1', 0);
INSERT INTO `t_comment` VALUES (20, '你好', 0, NULL, 1, '2023-11-01 16:24:25', '58', '1', 0);
INSERT INTO `t_comment` VALUES (21, 'string', 0, 20, 1, '2023-11-01 16:24:38', '58', '1', 0);
INSERT INTO `t_comment` VALUES (22, '你好', 0, 1, 1, '2023-11-01 16:29:24', '58', '1', 0);
INSERT INTO `t_comment` VALUES (23, '999999', 0, 1, 1, '2023-11-01 16:30:40', '58', '1', 1);
INSERT INTO `t_comment` VALUES (24, 'string', 0, 20, 1, '2023-11-01 16:31:37', '58', '1', 1);
INSERT INTO `t_comment` VALUES (25, '790372137912372183792183798123217398198312793', 0, 1, 1, '2023-11-01 16:32:43', '58', '1', 1);
INSERT INTO `t_comment` VALUES (26, '1·2091·80928·1028·', 0, 20, 1, '2023-11-01 16:37:56', '58', '1', 1);
INSERT INTO `t_comment` VALUES (27, '法大师傅士大夫的', 0, 20, 1, '2023-11-01 16:39:56', '58', '1', 1);
INSERT INTO `t_comment` VALUES (28, '反对发射点犯得上房贷首付', 0, 20, 1, '2023-11-01 16:40:12', '58', '1', 1);
INSERT INTO `t_comment` VALUES (29, '大撒大撒大撒大撒', 0, 20, 1, '2023-11-01 16:44:00', '58', '1', 1);
INSERT INTO `t_comment` VALUES (30, '犯得上犯得上发射点', 0, 20, 1, '2023-11-01 16:44:42', '58', '1', 1);
INSERT INTO `t_comment` VALUES (31, '撒旦撒旦', 0, 20, 1, '2023-11-01 16:46:19', '58', '1', 1);
INSERT INTO `t_comment` VALUES (32, '的撒旦撒旦', 0, NULL, 1, '2023-11-01 16:53:26', '58', '1', 0);
INSERT INTO `t_comment` VALUES (33, 'string', 0, 32, 1, '2023-11-01 16:57:37', '58', '1', 1);
INSERT INTO `t_comment` VALUES (34, '的萨芬打赏', 0, 32, 1, '2023-11-01 17:03:52', '58', '1', 1);
INSERT INTO `t_comment` VALUES (35, '牛牛', 0, 32, 1, '2023-11-01 17:04:14', '58', '1', 1);
INSERT INTO `t_comment` VALUES (36, '方法士大夫士大夫但是', 0, 32, 1, '2023-11-01 17:05:47', '58', '1', 1);
INSERT INTO `t_comment` VALUES (37, '持续性出现', 0, 32, 1, '2023-11-01 17:06:18', '58', '1', 1);
INSERT INTO `t_comment` VALUES (38, '大撒大撒', 0, 16, 1, '2023-11-01 17:21:01', '58', '1', 1);
INSERT INTO `t_comment` VALUES (39, 'string', 0, 16, 1, '2023-11-01 17:21:47', '58', '1', 1);
INSERT INTO `t_comment` VALUES (40, '大撒大撒', 0, 16, 1, '2023-11-01 17:21:56', '58', '1', 1);
INSERT INTO `t_comment` VALUES (41, '的撒', 0, 16, 1, '2023-11-01 17:23:35', '58', '1', 1);
INSERT INTO `t_comment` VALUES (42, '大撒大撒 ', 0, 16, 1, '2023-11-01 17:23:46', '58', '1', 1);
INSERT INTO `t_comment` VALUES (43, '撒的撒  打赏的撒撒撒的撒大是打赏', 0, 16, 1, '2023-11-01 17:25:12', '58', '1', 1);
INSERT INTO `t_comment` VALUES (44, '的撒大苏打撒旦', 0, 32, 1, '2023-11-01 17:29:07', '58', '1', 1);
INSERT INTO `t_comment` VALUES (45, '321312312331232132131231', 0, 32, 1, '2023-11-01 17:29:13', '58', '1', 1);
INSERT INTO `t_comment` VALUES (46, '大撒大撒', 0, 20, 1, '2023-11-01 17:30:29', '58', '1', 1);
INSERT INTO `t_comment` VALUES (47, '0000000000000000000000000000', 0, 20, 1, '2023-11-01 17:30:45', '58', '1', 1);
INSERT INTO `t_comment` VALUES (48, '就会更加见过好几个', 0, 32, 1, '2023-11-01 17:31:44', '58', '1', 1);
INSERT INTO `t_comment` VALUES (49, '大撒大撒大苏打实打实水水', 0, 32, 1, '2023-11-01 17:32:08', '58', '1', 1);
INSERT INTO `t_comment` VALUES (50, 'string', 0, 32, 1, '2023-11-01 17:32:36', '58', '1', 1);
INSERT INTO `t_comment` VALUES (51, 'string', 0, 32, 1, '2023-11-01 17:32:37', '58', '1', 1);
INSERT INTO `t_comment` VALUES (52, '大撒大撒大撒', 0, 32, 1, '2023-11-01 17:32:42', '58', '1', 1);
INSERT INTO `t_comment` VALUES (53, '7988564432', 0, 32, 1, '2023-11-01 17:32:52', '58', '1', 1);
INSERT INTO `t_comment` VALUES (54, 'iuuuuu', 0, 32, 1, '2023-11-01 17:33:16', '58', '1', 1);
INSERT INTO `t_comment` VALUES (55, 'string', 0, 32, 1, '2023-11-01 17:34:08', '58', '1', 1);
INSERT INTO `t_comment` VALUES (56, '的撒旦撒旦', 0, 32, 1, '2023-11-01 17:34:14', '58', '1', 1);
INSERT INTO `t_comment` VALUES (57, '大撒大撒', 0, 32, 1, '2023-11-01 17:35:04', '58', '1', 1);
INSERT INTO `t_comment` VALUES (58, '的撒大苏打广泛广泛的', 0, 32, 1, '2023-11-01 18:17:43', '58', '1', 1);
INSERT INTO `t_comment` VALUES (59, 'string', 0, 32, 1, '2023-11-01 18:17:47', '58', '1', 1);
INSERT INTO `t_comment` VALUES (60, '1000000000000000000000', 0, 32, 1, '2023-11-01 18:17:56', '58', '1', 1);
INSERT INTO `t_comment` VALUES (61, '你好，很有趣的文章', 0, NULL, 1, '2023-11-01 18:18:20', '66', '1', 0);
INSERT INTO `t_comment` VALUES (62, 'string', 0, 61, 1, '2023-11-01 18:18:22', '66', '1', 1);
INSERT INTO `t_comment` VALUES (63, '你在说什么哈哈', 0, 61, 1, '2023-11-01 18:18:35', '66', '1', 1);
INSERT INTO `t_comment` VALUES (64, 'string', 0, 61, 1, '2023-11-03 13:18:48', '66', '1', 1);
INSERT INTO `t_comment` VALUES (65, '你好呀无趣的文章', 0, 61, 1, '2023-11-03 13:19:00', '66', '1', 1);
INSERT INTO `t_comment` VALUES (66, '可恶', 0, 61, 1, '2023-11-03 13:19:45', '66', '1', 1);
INSERT INTO `t_comment` VALUES (67, '我感觉挺奇怪的', 0, NULL, 1, '2023-11-03 18:28:25', '66', '1', 0);
INSERT INTO `t_comment` VALUES (68, '的撒大苏打', 0, NULL, 1, '2023-11-03 18:30:16', '66', '1', 0);
INSERT INTO `t_comment` VALUES (69, '6666', 0, NULL, 1, '2023-11-03 18:44:16', '66', '1', 0);
INSERT INTO `t_comment` VALUES (70, '9999', 0, NULL, 1, '2023-11-03 18:45:20', '66', '1', 0);
INSERT INTO `t_comment` VALUES (71, '不是吧，你这写的是什么', 0, NULL, 1, '2023-11-03 18:45:34', '66', '1', 0);
INSERT INTO `t_comment` VALUES (72, '你真的搞笑', 0, NULL, 1, '2023-11-03 18:47:49', '66', '1', 0);
INSERT INTO `t_comment` VALUES (73, '哈哈哈', 0, NULL, 1, '2023-11-03 19:17:23', '66', '1', 0);
INSERT INTO `t_comment` VALUES (74, '牛的', 0, NULL, 1, '2023-11-03 19:17:46', '66', '1', 0);
INSERT INTO `t_comment` VALUES (75, '的撒旦', 0, NULL, 1, '2023-11-03 19:17:58', '66', '1', 0);
INSERT INTO `t_comment` VALUES (76, '你在说什么', 0, NULL, 1, '2023-11-03 19:32:35', '66', '1', 0);
INSERT INTO `t_comment` VALUES (77, '不关你的事', 0, NULL, 1, '2023-11-03 19:33:04', '66', '1', 0);
INSERT INTO `t_comment` VALUES (78, '房贷首付', 0, NULL, 1, '2023-11-03 19:34:37', '66', '1', 0);
INSERT INTO `t_comment` VALUES (79, '的撒大苏打', 0, NULL, 1, '2023-11-03 19:37:13', '66', '1', 0);
INSERT INTO `t_comment` VALUES (80, '打赏', 0, NULL, 1, '2023-11-03 19:39:56', '66', '1', 0);
INSERT INTO `t_comment` VALUES (81, '大撒大撒', 0, NULL, 1, '2023-11-03 19:40:26', '66', '1', 0);
INSERT INTO `t_comment` VALUES (82, '99999', 0, NULL, 1, '2023-11-03 19:40:39', '66', '1', 0);
INSERT INTO `t_comment` VALUES (83, '大撒大撒', 0, NULL, 1, '2023-11-03 19:41:17', '66', '1', 0);
INSERT INTO `t_comment` VALUES (84, '123213213', 0, NULL, 1, '2023-11-03 19:41:22', '66', '1', 0);
INSERT INTO `t_comment` VALUES (85, '大撒大撒', 0, NULL, 1, '2023-11-03 19:41:59', '66', '1', 0);
INSERT INTO `t_comment` VALUES (86, '的撒旦', 0, NULL, 1, '2023-11-03 19:43:09', '66', '1', 0);
INSERT INTO `t_comment` VALUES (87, '大撒大撒大撒大撒大撒大撒顶顶顶顶顶顶顶131', 0, NULL, 1, '2023-11-03 19:43:20', '66', '1', 0);
INSERT INTO `t_comment` VALUES (88, '大撒大撒', 0, NULL, 1, '2023-11-03 19:44:48', '66', '1', 0);
INSERT INTO `t_comment` VALUES (89, '大撒大撒', 0, NULL, 1, '2023-11-03 19:44:53', '66', '1', 0);
INSERT INTO `t_comment` VALUES (90, '范德萨范德萨发达', 0, NULL, 1, '2023-11-03 19:49:23', '66', '1', 0);
INSERT INTO `t_comment` VALUES (91, '的撒旦撒旦', 0, NULL, 1, '2023-11-03 19:49:53', '66', '1', 0);
INSERT INTO `t_comment` VALUES (92, '大撒大撒', 0, NULL, 1, '2023-11-03 19:50:12', '66', '1', 0);
INSERT INTO `t_comment` VALUES (93, '===', 0, NULL, 1, '2023-11-03 19:50:17', '66', '1', 0);
INSERT INTO `t_comment` VALUES (94, '大苏打的', 0, NULL, 1, '2023-11-03 19:57:39', '66', '1', 0);
INSERT INTO `t_comment` VALUES (95, '的撒旦撒旦', 0, NULL, 1, '2023-11-03 19:57:42', '66', '1', 0);
INSERT INTO `t_comment` VALUES (96, '100000', 0, NULL, 1, '2023-11-03 19:59:31', '66', '1', 0);
INSERT INTO `t_comment` VALUES (97, '的撒旦撒旦', 0, NULL, 1, '2023-11-03 20:03:17', '66', '1', 0);
INSERT INTO `t_comment` VALUES (98, '大撒大撒', 0, NULL, 1, '2023-11-03 20:05:13', '66', '1', 0);
INSERT INTO `t_comment` VALUES (99, '大撒大撒', 0, NULL, 1, '2023-11-03 20:06:55', '66', '1', 0);
INSERT INTO `t_comment` VALUES (100, '大撒大撒', 0, NULL, 1, '2023-11-03 20:07:26', '66', '1', 0);
INSERT INTO `t_comment` VALUES (101, '大撒大撒大撒', 0, NULL, 1, '2023-11-03 20:07:31', '66', '1', 0);
INSERT INTO `t_comment` VALUES (102, '大撒大撒', 0, 67, 1, '2023-11-03 20:11:07', '66', '1', 1);
INSERT INTO `t_comment` VALUES (103, '你在说什么？', 0, 101, 1, '2023-11-03 20:13:31', '66', '1', 1);
INSERT INTO `t_comment` VALUES (104, '你好', 0, NULL, 1, '2023-11-05 22:30:50', '67', '1', 0);
INSERT INTO `t_comment` VALUES (105, '啊哈', 0, 104, 1, '2023-11-05 22:30:56', '67', '1', 1);
INSERT INTO `t_comment` VALUES (106, '规划', 0, NULL, 1, '2023-11-08 17:08:46', '69', '7', 0);
INSERT INTO `t_comment` VALUES (107, '哈哈哈', 0, NULL, 1, '2023-11-08 17:15:02', '69', '7', 0);
INSERT INTO `t_comment` VALUES (108, '你好', 0, 107, 1, '2023-11-08 17:15:27', '69', '1', 1);
INSERT INTO `t_comment` VALUES (109, '好什么', 0, 107, 1, '2023-11-08 17:15:53', '69', '1', 1);
INSERT INTO `t_comment` VALUES (110, '不理解', 0, 107, 1, '2023-11-08 17:16:57', '69', '1', 1);
INSERT INTO `t_comment` VALUES (111, 'hhhh ', 0, NULL, 1, '2023-11-08 22:49:45', '69', '7', 0);
INSERT INTO `t_comment` VALUES (112, '999', 0, NULL, 1, '2023-11-08 22:51:53', '69', '7', 0);
INSERT INTO `t_comment` VALUES (113, 'dsadsada', 0, NULL, 1, '2023-11-08 22:52:47', '69', '7', 0);
INSERT INTO `t_comment` VALUES (114, 'dsadsadsad', 0, NULL, 1, '2023-11-08 22:53:13', '68', '1', 0);
INSERT INTO `t_comment` VALUES (115, '你好', 0, NULL, 1, '2023-11-09 11:39:29', '68', '1', 0);
INSERT INTO `t_comment` VALUES (116, '你也好', 0, 115, 1, '2023-11-09 11:39:46', '68', '1', 1);

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `follow_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_follow
-- ----------------------------
INSERT INTO `t_follow` VALUES (1, 1, 1);
INSERT INTO `t_follow` VALUES (2, 1, 7);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `permit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 'menu:add', '/menu/**');
INSERT INTO `t_menu` VALUES (2, NULL, '/role/**');
INSERT INTO `t_menu` VALUES (3, '', '/post/**');
INSERT INTO `t_menu` VALUES (4, '', '/comment/**');
INSERT INTO `t_menu` VALUES (5, NULL, '/roleMenu/**');
INSERT INTO `t_menu` VALUES (6, NULL, '/user/**');
INSERT INTO `t_menu` VALUES (7, NULL, '/userRole/**');

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除字段 1 为删除',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0 已发布 1 草稿',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_post
-- ----------------------------
INSERT INTO `t_post` VALUES (58, '的撒旦', '2023-10-29 18:30:49', 0, 0, '2023-10-31 17:54:45', 1);
INSERT INTO `t_post` VALUES (59, '的撒旦撒旦', '2023-10-29 18:30:51', 0, 0, '2023-10-31 17:54:45', 1);
INSERT INTO `t_post` VALUES (60, '的撒大苏打', '2023-10-29 18:31:14', 0, 0, '2023-10-31 17:54:45', 1);
INSERT INTO `t_post` VALUES (61, '1234', '2023-10-29 20:31:30', 0, 0, '2023-10-31 17:54:46', 1);
INSERT INTO `t_post` VALUES (62, '312312312', '2023-10-29 20:31:34', 0, 0, '2023-10-31 17:54:46', 1);
INSERT INTO `t_post` VALUES (63, '的撒旦', '2023-10-29 20:59:17', 0, 0, '2023-10-31 17:54:46', 1);
INSERT INTO `t_post` VALUES (64, '的撒旦', '2023-10-29 21:00:02', 0, 0, '2023-10-31 17:54:47', 1);
INSERT INTO `t_post` VALUES (65, '111111111111', '2023-10-29 21:00:08', 0, 0, '2023-10-31 17:54:47', 1);
INSERT INTO `t_post` VALUES (66, 'iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu', '2023-10-30 00:42:12', 0, 0, '2023-10-31 17:54:51', 1);
INSERT INTO `t_post` VALUES (67, '大家好，我是发帖', '2023-11-04 16:53:40', 0, 0, '2023-11-04 16:53:40', 1);
INSERT INTO `t_post` VALUES (68, 'dsadsadsad ', '2023-11-07 21:23:07', 0, 0, '2023-11-07 21:23:07', 1);
INSERT INTO `t_post` VALUES (69, 'dsdsadsdssssss', '2023-11-07 21:25:48', 0, 0, '2023-11-07 21:25:48', 7);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', 'super_admin');
INSERT INTO `t_role` VALUES (2, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (3, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (4, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (5, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (6, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (7, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (8, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (9, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (10, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (11, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (12, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (13, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (14, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (15, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (16, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (17, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (18, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (19, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (20, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (21, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (22, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (23, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (24, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (25, NULL, 'normal_admin');
INSERT INTO `t_role` VALUES (26, NULL, 'normal_admin');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 1, 6);
INSERT INTO `t_role_menu` VALUES (3, 1, 2);
INSERT INTO `t_role_menu` VALUES (4, 1, 3);
INSERT INTO `t_role_menu` VALUES (5, 1, 4);
INSERT INTO `t_role_menu` VALUES (6, 1, 5);
INSERT INTO `t_role_menu` VALUES (7, 1, 7);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `school_id` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '学号',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户姓名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除字段 1 为删除',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'https://minio.cclucky.top/cclucky-blog/avatar/66fa0f9e7bd2c29dae746bf578795b45.png', '2023433070102', 'cclucky', 'cclucky@cc.com', 0, '13211111111', '2023-10-26 21:06:32', '2023-11-07 16:50:31', '$2a$10$EqPryfxvuCq7QBuB/eNl3OCqkU8zuA7AzVBHezscTIDoJVCfzH4B2');
INSERT INTO `t_user` VALUES (7, 'http://dummyimage.com/100x100', '2023433070103', 'cc', 'o.yfrletggp@qq.com', 0, '18628274212', '2023-10-30 14:10:14', '2023-10-31 21:42:08', '$2a$10$EqPryfxvuCq7QBuB/eNl3OCqkU8zuA7AzVBHezscTIDoJVCfzH4B2');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (7, 1, 7);

SET FOREIGN_KEY_CHECKS = 1;
