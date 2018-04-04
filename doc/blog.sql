/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-04 11:17:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_base_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_base_info`;
CREATE TABLE `blog_base_info` (
  `id` varchar(36) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `key_word` text,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(255) DEFAULT NULL,
  `comment_number` int(11) DEFAULT NULL,
  `like_number` int(11) DEFAULT NULL,
  `look_number` int(11) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '博客基本信息表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of blog_base_info
-- ----------------------------
INSERT INTO `blog_base_info` VALUES ('44524534', '车库', '对工单升格案爆发吧啥', '2018-04-03 17:31:25', '2', '45', '2', '230', '4563543', '0');
