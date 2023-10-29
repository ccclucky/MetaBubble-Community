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

 Date: 26/10/2023 10:45:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `user_id` int(11) NOT NULL COMMENT '评论用户id',
  `reply_user_id` int(11) NOT NULL COMMENT '回复用户id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父评论id',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除字段 1 为删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除字段 1 为删除',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0 已发布 1 草稿',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_post
-- ----------------------------
INSERT INTO `t_post` VALUES (1, 1, 'wqewq', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (2, 1, 'dsd', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (3, 1, 'dasds', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (4, 1, 'dsads', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (5, 1, '', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (6, 1, 'dsada', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (7, 1, 'fda', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (8, 1, 'dsds', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (9, 1, '11232222222222222222222222222222222222222222222222222222222222222222222', NULL, 1, NULL, NULL);
INSERT INTO `t_post` VALUES (10, 423, '432', 0, 0, '2023-10-26 01:42:41', '2023-10-26 01:42:41');
INSERT INTO `t_post` VALUES (11, 1, 'test1', NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户姓名',
  `school_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '学号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `pssword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除字段 1 为删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
