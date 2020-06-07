/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : shiro_thymeleaf

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 06/06/2020 18:25:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT 0,
  `level` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '系统管理', '#', 0, 0);
INSERT INTO `permission` VALUES (2, '用户管理', '/user/select', 1, 1);
INSERT INTO `permission` VALUES (3, '添加用户', '/user/insert', 2, 2);
INSERT INTO `permission` VALUES (4, '修改用户', '/user/update', 2, 2);
INSERT INTO `permission` VALUES (5, '删除用户', '/user/delete', 2, 2);
INSERT INTO `permission` VALUES (6, '分配角色', '/user/assign', 2, 2);
INSERT INTO `permission` VALUES (7, '角色管理', '/role/select', 1, 1);
INSERT INTO `permission` VALUES (8, '添加角色', '/role/insert', 7, 2);
INSERT INTO `permission` VALUES (9, '修改角色', '/role/update', 7, 2);
INSERT INTO `permission` VALUES (10, '删除角色', '/role/delete', 7, 2);
INSERT INTO `permission` VALUES (11, '分配权限', '/role/assign', 7, 2);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员');

-- ----------------------------
-- Table structure for rp
-- ----------------------------
DROP TABLE IF EXISTS `rp`;
CREATE TABLE `rp`  (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NULL DEFAULT 0,
  `pid` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`rpid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rp
-- ----------------------------
INSERT INTO `rp` VALUES (1, 1, 1);
INSERT INTO `rp` VALUES (2, 1, 2);
INSERT INTO `rp` VALUES (3, 1, 3);
INSERT INTO `rp` VALUES (4, 1, 4);
INSERT INTO `rp` VALUES (5, 1, 5);
INSERT INTO `rp` VALUES (6, 1, 6);
INSERT INTO `rp` VALUES (7, 1, 7);
INSERT INTO `rp` VALUES (8, 1, 8);
INSERT INTO `rp` VALUES (9, 1, 9);
INSERT INTO `rp` VALUES (10, 1, 10);
INSERT INTO `rp` VALUES (11, 1, 11);

-- ----------------------------
-- Table structure for ur
-- ----------------------------
DROP TABLE IF EXISTS `ur`;
CREATE TABLE `ur`  (
  `urid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT 0,
  `rid` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`urid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ur
-- ----------------------------
INSERT INTO `ur` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT 0,
  `age` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'cwCmquUucA1xvoBNM4TjmA==', '13612345678', 0, 20);

SET FOREIGN_KEY_CHECKS = 1;
