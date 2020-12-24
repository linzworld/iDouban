/*
 Navicat Premium Data Transfer

 Source Server         : 我的阿里云
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.102.212.18:3306
 Source Schema         : douban

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 22/12/2020 02:06:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a_article
-- ----------------------------
DROP TABLE IF EXISTS `a_article`;
CREATE TABLE `a_article`  (
  `article_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '文章id值',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `author` int(200) NOT NULL COMMENT '文章作者id',
  `published_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '文章发表的时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章的内容（HTML）',
  `collection` int(20) NOT NULL DEFAULT 0 COMMENT '文章收藏数',
  `share` int(20) NOT NULL DEFAULT 0 COMMENT '文章转发数',
  `comment` int(20) NOT NULL DEFAULT 0 COMMENT '文章评论数',
  `star` int(20) NOT NULL DEFAULT 0 COMMENT '文章点赞数',
  `stick` int(10) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  `page_view` int(20) NOT NULL DEFAULT 0 COMMENT '浏览量',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 172 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for a_collection
-- ----------------------------
DROP TABLE IF EXISTS `a_collection`;
CREATE TABLE `a_collection`  (
  `collection_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '文章收藏表的id',
  `user_id` int(200) NOT NULL COMMENT '进行收藏操作的用户',
  `article_id` int(200) NOT NULL COMMENT '被收藏的文章id',
  PRIMARY KEY (`collection_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `a_collection_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `a_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for a_comment
-- ----------------------------
DROP TABLE IF EXISTS `a_comment`;
CREATE TABLE `a_comment`  (
  `comment_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '文章评论表的id',
  `article_id` int(200) NOT NULL COMMENT '被评论的文章的id',
  `user_id` int(200) NOT NULL COMMENT '进行评论的用户id',
  `c_msg` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论的内容',
  `c_star` int(20) NOT NULL DEFAULT 0 COMMENT '点赞数，默认为0',
  `c_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论的时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `aricle_id`(`article_id`) USING BTREE,
  CONSTRAINT `a_comment_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `a_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for a_reply
-- ----------------------------
DROP TABLE IF EXISTS `a_reply`;
CREATE TABLE `a_reply`  (
  `reply_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '对评论下进行回复的id',
  `comment_id` int(200) NOT NULL COMMENT '被回复的评论id',
  `from_user_id` int(200) NOT NULL COMMENT '发出回复的人的id值',
  `to_user_id` int(200) NOT NULL COMMENT '接收回复的人的id值',
  `reply_msg` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复的内容',
  `reply_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '回复的时间',
  `r_star` int(20) NOT NULL DEFAULT 0 COMMENT '回复的点赞数',
  PRIMARY KEY (`reply_id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  CONSTRAINT `a_reply_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `a_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for a_share
-- ----------------------------
DROP TABLE IF EXISTS `a_share`;
CREATE TABLE `a_share`  (
  `share_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '转发文章表的id值',
  `user_id` int(200) NOT NULL COMMENT '进行转发的用户名的id值',
  `article_id` int(200) NOT NULL COMMENT '被转发的文章的id值',
  PRIMARY KEY (`share_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `a_share_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `a_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for a_star
-- ----------------------------
DROP TABLE IF EXISTS `a_star`;
CREATE TABLE `a_star`  (
  `star_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '点赞表的id',
  `type_id` int(200) NOT NULL COMMENT '被点赞的对象id',
  `type` int(200) NOT NULL COMMENT '被点赞的对象类型-1-文章 2-评论 3-回复',
  `user_id` int(200) NOT NULL COMMENT '进行点赞的用户id',
  PRIMARY KEY (`star_id`) USING BTREE,
  INDEX `a_star_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `a_star_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for a_tag
-- ----------------------------
DROP TABLE IF EXISTS `a_tag`;
CREATE TABLE `a_tag`  (
  `tag_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '文章分类表的id值',
  `tag_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章分类名',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_to_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_to_tag`;
CREATE TABLE `article_to_tag`  (
  `middle_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '充当文章表和标签表之间的中间表',
  `article_id` int(200) NOT NULL COMMENT '文章表的id',
  `tag_id` int(200) NOT NULL COMMENT '标签表的id',
  PRIMARY KEY (`middle_id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `article_to_tag_ibfk_2`(`tag_id`) USING BTREE,
  CONSTRAINT `article_to_tag_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `a_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `article_to_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `a_tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for doumail
-- ----------------------------
DROP TABLE IF EXISTS `doumail`;
CREATE TABLE `doumail`  (
  `doumail_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '豆邮表的id',
  `from_user_id` int(20) NOT NULL COMMENT '发出豆邮的用户的id',
  `to_user_id` int(20) NOT NULL COMMENT '接收豆邮的用户的id',
  `chat_msg` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '豆邮的内容',
  `chat_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '聊天的时间',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '记录删除状态，0为双方都没有删除，1为豆邮发出者删除，2为豆邮接收者删除',
  `read` int(11) NOT NULL DEFAULT 0 COMMENT '0为未读状态，1为已读状态',
  PRIMARY KEY (`doumail_id`) USING BTREE,
  INDEX `from_user_id`(`from_user_id`) USING BTREE,
  INDEX `to_user_id`(`to_user_id`) USING BTREE,
  CONSTRAINT `doumail_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `doumail_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `friend_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '好友的id值',
  `from_user_id` int(200) NOT NULL COMMENT 'a用户名id（该记录的发出者）',
  `to_user_id` int(200) NOT NULL COMMENT 'b用户名id（该记录的接收者）',
  `status` int(10) NOT NULL DEFAULT 1 COMMENT '1->a关注b 2->ab为好友（双向关注）3->b被a拉黑',
  `group_id` int(200) NULL DEFAULT NULL COMMENT '好友分组表的id值',
  PRIMARY KEY (`friend_id`) USING BTREE,
  INDEX `to_user_id`(`to_user_id`) USING BTREE,
  INDEX `from_user_id`(`from_user_id`) USING BTREE,
  INDEX `group_id`(`group_id`) USING BTREE,
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_ibfk_3` FOREIGN KEY (`group_id`) REFERENCES `friend_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friend_group
-- ----------------------------
DROP TABLE IF EXISTS `friend_group`;
CREATE TABLE `friend_group`  (
  `group_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '好友分组表的主键id',
  `group_name_to` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '好友分组名',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(40) NOT NULL AUTO_INCREMENT COMMENT '用户名的id',
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（通常为邮箱）',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码（MD5）',
  `status` int(20) NOT NULL DEFAULT 0 COMMENT '用户的权限（0为普通用户，1为管理员）',
  `reported` int(20) NOT NULL DEFAULT 0 COMMENT '用户是否被举报',
  `title_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '封号截止时间',
  `portrait` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://47.102.212.18/DouBan/image/default.png' COMMENT '用户头像（存储路径）',
  `signature` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `self_introduction` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自我介绍',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '某某' COMMENT '昵称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '用户注册时间',
  `code` int(100) NULL DEFAULT NULL COMMENT '用户的找回密码的凭证（UUID）',
  `out_time` timestamp(0) NULL DEFAULT NULL COMMENT '用户找回密码的时间限制（结束的时间）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
