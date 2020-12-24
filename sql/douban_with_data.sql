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

 Date: 22/12/2020 02:06:04
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
-- Records of a_article
-- ----------------------------
INSERT INTO `a_article` VALUES (3, '我是标题', 1, '2019-05-11 04:35:55', '<div> 我是文章内容 </div>', 0, 0, 1, 0, 0, 0);
INSERT INTO `a_article` VALUES (4, '我是标题 ', 1, '2019-05-08 00:00:00', '<div> 我是文章内容 </div>', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (64, '我是标题', 1, '2019-05-08 00:00:00', '<div> 我是文章内容 </div>', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (79, '我是标题', 1, '2019-05-11 00:00:00', '<div> 我是文章内容 </div>', 0, 0, 2, 0, 0, 0);
INSERT INTO `a_article` VALUES (104, '我是标题', 1, '2019-05-11 00:00:00', '<div>测试</div>', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (107, '我是标题', 1, '2019-05-11 00:00:00', '<div>我是文章内容</div><br><br><br><img src=\" http://47.102.212.18/DouBan/image/201905100041251677.jpg\" style=\"max-width:590px;\"></div><br><br>', 0, 0, 3, 0, 0, 0);
INSERT INTO `a_article` VALUES (108, '我是标题', 1, '2019-05-11 00:00:00', '\r\n			<br><br><br><br><br>		   \r\n					   <div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><img src=\" http://47.102.212.18/DouBan/image/201905101027173996.jpg\" style=\"max-width:590px;\"></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br></div><br><br>', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (109, '我是标题', 1, '2019-05-11 00:00:00', '<div>小时候不懂为什么香港要拍一支叫‘生活满希望，前路由我创’的电视广告，长大后终于懂了。原来都是说给长大的我们听的啊。原来一代又一代的年轻人都如此，政府看在眼里，又不知道可以做点什么，只能拍支广告鼓励一下。<br></div><div><br></div><div><br></div>\r\n					   \r\n					   <div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><img src=\" http://47.102.212.18/DouBan/image/201905101040263808.jpg\" style=\"max-width:590px;\"></div><br><div><br></div><div>又比如建筑。为了躲开人潮，我们深入到居民区，狭窄的小巷顿时只剩下了零星几个人。一个小小的海岛，竟密密麻麻地堆满了房屋，由于坡度不一，看上去房屋上面还有房屋，好像有个巨人在砌一堆积木，但是地方又不够，只能层层叠叠地往上垒，但又不至于像城市里的高楼那样。</div>', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (111, '我是标题', 1, '2019-05-11 01:54:46', '内容', 0, 0, 4, 0, 0, 0);
INSERT INTO `a_article` VALUES (112, '深夜发文章', 1, '2019-05-11 00:31:38', '我怕是疯了\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (114, '我是标题', 1, '2019-05-11 21:00:38', '我是文章内容', 0, 0, 1, 0, 0, 0);
INSERT INTO `a_article` VALUES (119, '我也会写文章', 50, '2019-05-13 03:51:08', '消灭人类暴政，世界属于三体<div><br></div><div><br><br><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><img src=\" http://47.102.212.18/DouBan/image/201905130350599121.jpg\" style=\"max-width:590px;\"></div><br><br>', 0, 0, 5, 0, 0, 0);
INSERT INTO `a_article` VALUES (122, '测试xss2', 1, '2019-05-13 20:52:48', '<div><span style=\"white-space:pre\">	</span>&nbsp; &nbsp;</div><div><span style=\"white-space:pre\">					</span>&nbsp; &nbsp;&lt;div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"&gt;&lt;br&gt;&lt;/div&gt;&lt;div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"&gt;&lt;br&gt;&lt;/div&gt;&lt;div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"&gt;&lt;img src=\" http://47.102.212.18/DouBan/image/201905112323418873.jpg\" style=\"max-width: 590px;\"&gt;&lt;br&gt;&lt;/div&gt;&lt;br&gt;&lt;br&gt;</div>\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (123, '测试xss3', 1, '2019-05-13 20:53:47', '&lt;script&gt;alert(12345)&lt;/script&gt;', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (132, '我是标题--测试1', 50, '2019-05-14 01:56:17', '我是内容1', 0, 0, 1, 0, 0, 0);
INSERT INTO `a_article` VALUES (133, '我是标题--测试2', 50, '2019-05-14 01:56:17', '我是内容2', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (134, '我是标题--测试3', 50, '2019-05-14 01:56:18', '我是内容3', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (135, '我是标题--测试4', 50, '2019-05-14 01:56:18', '我是内容4', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (136, '我是标题--测试5', 50, '2019-05-14 01:56:18', '我是内容5', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (137, '我是标题--测试6', 50, '2019-05-14 01:56:18', '我是内容6', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (138, '我是标题--测试7', 50, '2019-05-14 01:56:18', '我是内容7', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (139, '我是标题--测试8', 50, '2019-05-14 01:56:18', '我是内容8', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (140, '我是标题--测试9', 50, '2019-05-14 01:56:18', '我是内容9', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (143, '测试', 1, '2019-05-14 02:22:08', '内容123\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (145, '又是一个测试', 1, '2019-05-14 23:54:47', '文章测试', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (148, '123', 1, '2019-05-15 14:08:34', '321', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (150, '写文章', 1, '2019-05-15 20:00:28', '文章的格式\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (151, '写文章测试', 1, '2019-05-15 20:01:06', '文章内容\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (152, '写文章的', 1, '2019-05-15 20:06:26', '内容\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (153, '测试', 1, '2019-05-15 20:08:38', '文章内容\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (154, '测试', 1, '2019-05-15 20:09:24', '内容\r\n					   \r\n					   ', 1, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (155, '测试', 1, '2019-05-15 20:11:55', '为问我为问问&nbsp;为为为\r\n					   \r\n					   ', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (157, '标题', 1, '2019-05-16 12:43:57', '这是内容这是<b>内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这</b>是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这<font size=\"4\">是内容这是内容这</font>是内容这额外人为<div><br></div><div><br></div><div><br><div><br></div><div><br></div></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/DouBan/image/201905161242383692.jpg\" style=\"max-width:590px;\"></div><br><br>', 1, 1, 3, 1, 0, 0);
INSERT INTO `a_article` VALUES (158, 'NISHI WER DSFASDF ASDFA SDFA SD', 1, '2019-05-17 17:39:36', 'FA SDFAS DFASDFsegqweyqeryqerywerysryweryer', 0, 0, 3, 0, 0, 0);
INSERT INTO `a_article` VALUES (159, '标题', 56, '2019-05-19 10:20:41', 'sdaS放火焚烧的<i><strike>话费送的</strike></i>\r\n					   \r\n					   <div><i><strike><br></strike></i></div><div><i><strike><br></strike></i></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/DouBan/image/201905191020291375.jpg\" style=\"max-width:590px;\"></div><br><br>', 1, 0, 2, 1, 0, 0);
INSERT INTO `a_article` VALUES (166, '服务器中的图片测试', 1, '2019-09-24 21:45:25', '<br><br><br><br><br><br><br><br><br><br><br><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/DouBan/image/201909242144521364.jpg\" style=\"max-width:590px;\"></div><br>搜索<br><br>', 0, 0, 3, 0, 0, 0);
INSERT INTO `a_article` VALUES (167, '吹水区', 1, '2019-09-24 22:24:10', '<br><br><br><br><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/DouBan/image/201909242223312246.jpg\" style=\"max-width:590px;\"></div><br><br><br><br><br>', 1, 1, 2, 1, 0, 0);
INSERT INTO `a_article` VALUES (168, '我是标题', 1, '2019-09-27 09:53:44', '我是文章<div><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/DouBan/image/201909270952349833.jpg\" style=\"max-width:590px;\"></div><br><br>', 0, 0, 1, 0, 0, 0);
INSERT INTO `a_article` VALUES (169, '我是文章标题', 1, '2019-09-29 16:43:23', '内容<div><br></div><div><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/DouBan/image/201909291643168543.jpg\" style=\"max-width:590px;\"></div><br><br>', 0, 0, 1, 0, 0, 0);
INSERT INTO `a_article` VALUES (170, 'Beautiful Scenery', 1, '2020-08-01 14:10:01', '\r\n					   \r\n					   <div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\">巴拉巴拉<br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><img src=\"http://47.102.212.18/iDouBan/image/202008011408425334.png\" style=\"max-width: 590px;\"><br></div><br><br>', 0, 0, 0, 0, 0, 0);
INSERT INTO `a_article` VALUES (171, '标题11', 1, '2020-08-05 13:59:09', '内容\r\n					   \r\n					   <div><br></div><div id=\"editor-img-insert\" style=\"display: table;margin: 15px auto;\"><br><br><img src=\" http://47.102.212.18/iDouBan/image/202008051359022044.jpg\" style=\"max-width:590px;\"></div><br><br>', 0, 0, 0, 0, 0, 0);

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
-- Records of a_collection
-- ----------------------------
INSERT INTO `a_collection` VALUES (19, 1, 154);
INSERT INTO `a_collection` VALUES (21, 1, 157);
INSERT INTO `a_collection` VALUES (26, 56, 159);
INSERT INTO `a_collection` VALUES (31, 1, 167);

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
-- Records of a_comment
-- ----------------------------
INSERT INTO `a_comment` VALUES (18, 79, 1, '我是评论', 0, '2019-05-11 00:06:59');
INSERT INTO `a_comment` VALUES (35, 111, 21, '新用户已上场', 0, '2019-05-11 01:54:46');
INSERT INTO `a_comment` VALUES (52, 111, 1, '能回复吗，是真的吗', 0, '2019-05-11 05:05:52');
INSERT INTO `a_comment` VALUES (54, 107, 1, '天要亮了', 0, '2019-05-11 05:08:06');
INSERT INTO `a_comment` VALUES (76, 107, 1, '又要开始学了', 0, '2019-05-11 23:18:05');
INSERT INTO `a_comment` VALUES (77, 107, 1, '回复功能，要找到相对应的元素', 0, '2019-05-11 23:19:14');
INSERT INTO `a_comment` VALUES (79, 111, 1, '我说一句评论', 0, '2019-05-12 04:34:11');
INSERT INTO `a_comment` VALUES (129, 119, 50, '开天辟地第一人', 0, '2019-05-13 03:53:31');
INSERT INTO `a_comment` VALUES (131, 119, 1, '开天辟地第一人', 0, '2019-05-13 04:26:28');
INSERT INTO `a_comment` VALUES (132, 119, 1, '开天辟地第一人', 0, '2019-05-13 04:26:47');
INSERT INTO `a_comment` VALUES (133, 119, 1, '开天辟地第一人', 0, '2019-05-13 04:28:07');
INSERT INTO `a_comment` VALUES (134, 119, 1, '开天辟地第一人', 0, '2019-05-13 04:28:11');
INSERT INTO `a_comment` VALUES (135, 114, 1, '评论', 0, '2019-05-13 20:38:36');
INSERT INTO `a_comment` VALUES (144, 157, 1, '这是一条评论', 0, '2019-05-16 12:47:29');
INSERT INTO `a_comment` VALUES (145, 157, 1, '这是一条评论', 0, '2019-05-16 12:47:36');
INSERT INTO `a_comment` VALUES (146, 157, 1, '这是一条评论', 1, '2019-05-16 12:47:40');
INSERT INTO `a_comment` VALUES (147, 158, 1, 'sdfasgsagasdgasdfasdf', 0, '2019-05-17 17:39:44');
INSERT INTO `a_comment` VALUES (149, 159, 56, '趣味', 0, '2019-05-19 10:21:16');
INSERT INTO `a_comment` VALUES (150, 159, 56, '无服务', 1, '2019-05-19 10:24:19');
INSERT INTO `a_comment` VALUES (151, 132, 1, 'we ', 1, '2019-07-17 17:45:39');
INSERT INTO `a_comment` VALUES (154, 166, 1, '额我认为二', 1, '2019-09-24 21:46:50');
INSERT INTO `a_comment` VALUES (155, 166, 1, '二位惹我', 1, '2019-09-24 21:46:55');
INSERT INTO `a_comment` VALUES (156, 166, 1, '撒都发', 0, '2019-09-24 21:47:18');
INSERT INTO `a_comment` VALUES (157, 168, 1, '是谷歌', 0, '2019-09-27 09:54:04');
INSERT INTO `a_comment` VALUES (158, 169, 1, 'cxk真美', 0, '2019-09-29 16:45:35');
INSERT INTO `a_comment` VALUES (159, 167, 1, 'sdfsdf', 1, '2019-10-01 16:23:26');
INSERT INTO `a_comment` VALUES (160, 167, 1, 'dsadas ', 0, '2019-10-17 20:55:53');
INSERT INTO `a_comment` VALUES (161, 158, 1, '什么玩意', 0, '2020-08-05 14:04:54');
INSERT INTO `a_comment` VALUES (162, 158, 1, '什么玩意', 0, '2020-08-05 14:05:04');

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
-- Records of a_reply
-- ----------------------------
INSERT INTO `a_reply` VALUES (2, 79, 1, 21, '我是回复', '2019-05-12 06:29:05', 0);
INSERT INTO `a_reply` VALUES (3, 79, 21, 1, '我是回复', '2019-05-12 06:31:29', 0);
INSERT INTO `a_reply` VALUES (47, 144, 1, 1, '进行回复', '2019-05-16 13:10:24', 0);
INSERT INTO `a_reply` VALUES (48, 144, 1, 1, '进行回复', '2019-05-16 13:10:45', 0);
INSERT INTO `a_reply` VALUES (49, 144, 1, 1, '进行回复', '2019-05-16 13:11:30', 0);
INSERT INTO `a_reply` VALUES (50, 149, 56, 56, '回复', '2019-05-19 10:21:33', 0);
INSERT INTO `a_reply` VALUES (54, 155, 1, 1, '说的发生地方', '2019-09-24 21:47:13', 0);
INSERT INTO `a_reply` VALUES (56, 159, 1, 1, 'asfasf', '2019-10-08 16:20:03', 0);
INSERT INTO `a_reply` VALUES (57, 159, 1, 1, 'fasdfasdfasdf', '2019-10-17 20:56:01', 0);

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
-- Records of a_share
-- ----------------------------
INSERT INTO `a_share` VALUES (10, 1, 157);
INSERT INTO `a_share` VALUES (17, 1, 167);

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
-- Records of a_star
-- ----------------------------
INSERT INTO `a_star` VALUES (52, 115, 1, 1);
INSERT INTO `a_star` VALUES (53, 2, 1, 1);
INSERT INTO `a_star` VALUES (54, 124, 1, 21);
INSERT INTO `a_star` VALUES (57, 136, 2, 21);
INSERT INTO `a_star` VALUES (61, 140, 2, 21);
INSERT INTO `a_star` VALUES (62, 83, 2, 21);
INSERT INTO `a_star` VALUES (63, 75, 2, 21);
INSERT INTO `a_star` VALUES (65, 75, 2, 1);
INSERT INTO `a_star` VALUES (66, 63, 2, 1);
INSERT INTO `a_star` VALUES (68, 55, 2, 1);
INSERT INTO `a_star` VALUES (70, 140, 2, 1);
INSERT INTO `a_star` VALUES (72, 143, 2, 1);
INSERT INTO `a_star` VALUES (75, 157, 1, 1);
INSERT INTO `a_star` VALUES (77, 146, 2, 1);
INSERT INTO `a_star` VALUES (80, 80, 2, 1);
INSERT INTO `a_star` VALUES (82, 159, 1, 56);
INSERT INTO `a_star` VALUES (83, 150, 2, 56);
INSERT INTO `a_star` VALUES (84, 148, 2, 1);
INSERT INTO `a_star` VALUES (85, 164, 1, 1);
INSERT INTO `a_star` VALUES (86, 113, 1, 1);
INSERT INTO `a_star` VALUES (87, 58, 2, 1);
INSERT INTO `a_star` VALUES (89, 151, 2, 1);
INSERT INTO `a_star` VALUES (90, 152, 2, 1);
INSERT INTO `a_star` VALUES (91, 154, 2, 1);
INSERT INTO `a_star` VALUES (93, 155, 2, 1);
INSERT INTO `a_star` VALUES (94, 29, 2, 1);
INSERT INTO `a_star` VALUES (95, 61, 2, 1);
INSERT INTO `a_star` VALUES (96, 159, 2, 1);
INSERT INTO `a_star` VALUES (98, 167, 1, 1);

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
-- Records of a_tag
-- ----------------------------
INSERT INTO `a_tag` VALUES (1, '无分组');
INSERT INTO `a_tag` VALUES (2, '言论');
INSERT INTO `a_tag` VALUES (3, '思想');
INSERT INTO `a_tag` VALUES (4, '社会热点');
INSERT INTO `a_tag` VALUES (5, '情感');
INSERT INTO `a_tag` VALUES (6, '美食');
INSERT INTO `a_tag` VALUES (7, '读书');
INSERT INTO `a_tag` VALUES (8, '科技');
INSERT INTO `a_tag` VALUES (9, '学习');
INSERT INTO `a_tag` VALUES (10, 'NBA');
INSERT INTO `a_tag` VALUES (11, '小明');
INSERT INTO `a_tag` VALUES (12, '天文');
INSERT INTO `a_tag` VALUES (13, '科学');
INSERT INTO `a_tag` VALUES (14, '电影');
INSERT INTO `a_tag` VALUES (15, '音乐');
INSERT INTO `a_tag` VALUES (16, '其他');
INSERT INTO `a_tag` VALUES (28, '小明');
INSERT INTO `a_tag` VALUES (37, '美剧');
INSERT INTO `a_tag` VALUES (38, '测试');
INSERT INTO `a_tag` VALUES (39, 'SASD');
INSERT INTO `a_tag` VALUES (40, 'asdasdasdas');
INSERT INTO `a_tag` VALUES (41, '一个分组');
INSERT INTO `a_tag` VALUES (42, '1111');
INSERT INTO `a_tag` VALUES (43, 'sdfasdf');
INSERT INTO `a_tag` VALUES (44, 'undefined');

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
-- Records of article_to_tag
-- ----------------------------
INSERT INTO `article_to_tag` VALUES (46, 104, 38);
INSERT INTO `article_to_tag` VALUES (50, 107, 38);
INSERT INTO `article_to_tag` VALUES (51, 108, 38);
INSERT INTO `article_to_tag` VALUES (52, 109, 38);
INSERT INTO `article_to_tag` VALUES (54, 111, 4);
INSERT INTO `article_to_tag` VALUES (55, 112, 38);
INSERT INTO `article_to_tag` VALUES (61, 119, 38);
INSERT INTO `article_to_tag` VALUES (64, 122, 38);
INSERT INTO `article_to_tag` VALUES (65, 123, 38);
INSERT INTO `article_to_tag` VALUES (69, 143, 38);
INSERT INTO `article_to_tag` VALUES (71, 145, 38);
INSERT INTO `article_to_tag` VALUES (74, 148, 38);
INSERT INTO `article_to_tag` VALUES (76, 150, 38);
INSERT INTO `article_to_tag` VALUES (77, 151, 38);
INSERT INTO `article_to_tag` VALUES (78, 152, 38);
INSERT INTO `article_to_tag` VALUES (79, 153, 41);
INSERT INTO `article_to_tag` VALUES (80, 154, 38);
INSERT INTO `article_to_tag` VALUES (81, 155, 38);
INSERT INTO `article_to_tag` VALUES (83, 157, 38);
INSERT INTO `article_to_tag` VALUES (84, 158, 43);
INSERT INTO `article_to_tag` VALUES (85, 159, 38);
INSERT INTO `article_to_tag` VALUES (86, 159, 8);
INSERT INTO `article_to_tag` VALUES (89, 166, 16);
INSERT INTO `article_to_tag` VALUES (90, 167, 44);
INSERT INTO `article_to_tag` VALUES (91, 168, 4);
INSERT INTO `article_to_tag` VALUES (92, 169, 10);
INSERT INTO `article_to_tag` VALUES (93, 170, 16);
INSERT INTO `article_to_tag` VALUES (94, 171, 7);

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
-- Records of doumail
-- ----------------------------
INSERT INTO `doumail` VALUES (1, 21, 1, '数据库测试2222', '2019-05-14 06:01:08', 0, 0);
INSERT INTO `doumail` VALUES (2, 1, 21, '数据库连接11111111', '2019-05-14 08:41:27', 0, 0);
INSERT INTO `doumail` VALUES (3, 24, 1, '测试3', '2019-05-14 14:57:15', 0, 0);
INSERT INTO `doumail` VALUES (4, 1, 21, '增加数据2222222222222', '2019-05-14 19:16:17', 0, 0);
INSERT INTO `doumail` VALUES (5, 1, 21, '增加另外一条记录333', '2019-05-14 19:17:56', 0, 0);
INSERT INTO `doumail` VALUES (6, 21, 1, '测试4', '2019-05-14 19:20:30', 0, 0);
INSERT INTO `doumail` VALUES (7, 21, 21, '测试4444444444444', '2019-05-14 19:22:57', 0, 0);
INSERT INTO `doumail` VALUES (8, 21, 21, '测试555555555', '2019-05-14 19:23:28', 0, 0);
INSERT INTO `doumail` VALUES (9, 1, 21, '测试6666666666', '2019-05-14 19:24:05', 0, 0);
INSERT INTO `doumail` VALUES (10, 1, 24, '实时', '2019-05-14 19:24:30', 0, 0);
INSERT INTO `doumail` VALUES (11, 21, 1, '我', '2019-05-14 19:35:02', 0, 0);
INSERT INTO `doumail` VALUES (12, 1, 24, '测试无穷次', '2019-05-14 19:47:24', 0, 0);
INSERT INTO `doumail` VALUES (13, 1, 24, '你想干嘛', '2019-05-14 19:47:33', 0, 0);
INSERT INTO `doumail` VALUES (14, 1, 24, '我不跟你说话了', '2019-05-14 19:47:53', 0, 0);
INSERT INTO `doumail` VALUES (15, 1, 24, '好像可以实时聊天', '2019-05-14 19:48:08', 0, 0);
INSERT INTO `doumail` VALUES (16, 1, 24, '来个for循环怎么样', '2019-05-14 19:48:22', 0, 0);
INSERT INTO `doumail` VALUES (17, 1, 24, '&lt;script&gt;&lt;/script&gt;', '2019-05-14 19:49:43', 0, 0);
INSERT INTO `doumail` VALUES (18, 1, 24, '能分页嘛', '2019-05-14 19:50:26', 0, 0);
INSERT INTO `doumail` VALUES (19, 1, 24, '再水几句话', '2019-05-14 19:50:35', 0, 0);
INSERT INTO `doumail` VALUES (20, 1, 24, '再来一次', '2019-05-14 19:50:42', 0, 0);
INSERT INTO `doumail` VALUES (21, 1, 21, '你干嘛', '2019-05-14 19:54:08', 0, 0);
INSERT INTO `doumail` VALUES (22, 1, 21, '分页么', '2019-05-14 19:54:15', 0, 0);
INSERT INTO `doumail` VALUES (23, 1, 21, '分页', '2019-05-14 19:54:26', 0, 0);
INSERT INTO `doumail` VALUES (24, 1, 21, '分页呢', '2019-05-14 19:54:40', 0, 0);
INSERT INTO `doumail` VALUES (25, 1, 34, '你好吗', '2019-05-14 22:07:46', 0, 0);
INSERT INTO `doumail` VALUES (26, 1, 34, '测试标题', '2019-05-14 22:09:47', 0, 0);
INSERT INTO `doumail` VALUES (27, 1, 21, '分页', '2019-05-14 23:30:56', 0, 0);
INSERT INTO `doumail` VALUES (28, 1, 21, '小佬', '2019-05-15 06:16:51', 0, 0);
INSERT INTO `doumail` VALUES (29, 1, 23, '发出一封豆邮', '2019-05-15 18:30:13', 0, 0);
INSERT INTO `doumail` VALUES (30, 53, 1, '新用户', '2019-05-15 20:31:45', 0, 0);
INSERT INTO `doumail` VALUES (31, 53, 1, '新用户', '2019-05-15 20:32:29', 0, 0);
INSERT INTO `doumail` VALUES (32, 1, 53, '我发消息给你看看', '2019-05-15 20:38:56', 0, 0);
INSERT INTO `doumail` VALUES (33, 1, 1, '给我自个儿发豆邮', '2019-05-15 20:41:15', 0, 0);
INSERT INTO `doumail` VALUES (34, 22, 30, 'WOHFOEFWEOJO WEIHIAUDGASDG ', '2019-05-16 12:12:13', 0, 0);
INSERT INTO `doumail` VALUES (35, 22, 30, 'KJVH DIUA DIUFGSDFGOA ', '2019-05-16 12:12:18', 0, 0);
INSERT INTO `doumail` VALUES (36, 22, 30, 'LUSADA LBDSFLASDG BALDGFAS,DBN FLKJASDF ASD', '2019-05-16 12:12:23', 0, 0);
INSERT INTO `doumail` VALUES (37, 22, 30, 'MKHAKDSBKJFALDSFHSDFJKLKHBHJNKNGHNJ', '2019-05-16 12:12:32', 0, 0);
INSERT INTO `doumail` VALUES (38, 22, 30, 'SLAUFDSIALGDBSIUGABERLB', '2019-05-16 12:12:38', 0, 0);
INSERT INTO `doumail` VALUES (39, 22, 30, 'XJABGFDMGKGJLADALKSJDFKASDFKASDFASDFHASLKDFASDFASDFASKDJHFASD', '2019-05-16 12:12:47', 0, 0);
INSERT INTO `doumail` VALUES (40, 22, 30, ' jhbcvgyashdvjgtfdkjcvlgatoqgailsdutioagslHKGfdtodslgaftrq8gorayvtgosabjkfpsdbvlkawueitfvkjgwltagksdgvo lｓａｄｂｇｌａｉｕｄｓＧｎ．ｆｋｌｖｂｎｄｓｇｅｌａｂｖｋＪＤｙｇｌｋａｊｕｏｖｓｄｌｂａｊｆｉｔｏａｓｇｄｆ还是对方空间阿斯顿饭格拉底衫尴尬尴发了几套衣服看见帅哥非所答复议他还敢发看尽繁华高峰哥打通ａｌ．ｅｙｆ他了饭啊特工了几饿哦反应发音', '2019-05-16 12:13:56', 0, 0);
INSERT INTO `doumail` VALUES (41, 22, 30, 'jhbcvgyashdvjgtfdkjcvlgatoqgailsdutioagslHKGfdtodslgaftrq8gorayvtgosabjkfpsdbvlkawueitfvkjgwltagksdgvo lｓａｄｂｇｌａｉｕｄｓＧｎ．ｆｋｌｖｂｎｄｓｇｅｌａｂｖｋＪＤｙｇｌｋａｊｕｏｖｓｄｌｂａｊｆｉｔｏａｓｇｄｆ还是对方空间阿斯顿饭格拉底衫尴尬尴发了几套衣服看见帅哥非所答复议他还敢发看尽繁华高峰哥打通ａｌ．ｅｙｆ他了饭啊特工了几饿哦反应发音jhbcvgyashdvjgtfdkjcvlgatoqgailsdutioagslHKGfdtodslgaftrq8gorayvtgosabjkfpsdbvlkawueitfvkjgwltagksdgvo lｓａｄｂｇｌａｉｕｄｓＧｎ．ｆｋｌｖｂｎｄｓｇｅｌａｂｖｋＪＤｙｇｌｋａｊｕｏｖｓｄｌｂａｊｆｉｔｏａｓｇｄｆ还是对方空间阿斯顿饭格拉底衫尴尬尴发了几套衣服看见帅哥非所答复议他还敢发看尽繁华高峰哥打通ａｌ．ｅｙｆ他了饭啊特工了几饿哦反应发音', '2019-05-16 12:14:02', 0, 0);
INSERT INTO `doumail` VALUES (42, 22, 30, 'jhbcvgyashdvjgtfdkjcvlgatoqgailsdutioagslHKGfdtodslgaftrq8gorayvtgosabjkfpsdbvlkawueitfvkjgwltagksdgvo lｓａｄｂｇｌａｉｕｄｓＧｎ．ｆｋｌｖｂｎｄｓｇｅｌａｂｖｋＪＤｙｇｌｋａｊｕｏｖｓｄｌｂａｊｆｉｔｏａｓｇｄｆ还是对方空间阿斯顿饭格拉底衫尴尬尴发了几套衣服看见帅哥非所答复议他还敢发看尽繁华高峰哥打通ａｌ．ｅｙｆ他了饭啊特工了几饿哦反应发音jhbcvgyashdvjgtfdkjcvlgatoqgailsdutioagslHKGfdtodslgaftrq8gorayvtgosabjkfpsdbvlkawueitfvkjgwltagksdgvo lｓａｄｂｇｌａｉｕｄｓＧｎ．ｆｋｌｖｂｎｄｓｇｅｌａｂｖｋＪＤｙｇｌｋａｊｕｏｖｓｄｌｂａｊｆｉｔｏａｓｇｄｆ还是对方空间阿斯顿饭格拉底衫尴尬尴发了几套衣服看见帅哥非所答复议他还敢发看尽繁华高峰哥打通ａｌ．ｅｙｆ他了饭啊特工了几饿哦反应发音', '2019-05-16 12:14:07', 0, 0);
INSERT INTO `doumail` VALUES (43, 22, 21, '这是一封豆邮', '2019-05-16 12:30:10', 0, 0);
INSERT INTO `doumail` VALUES (44, 22, 21, '消息', '2019-05-16 12:30:18', 0, 0);
INSERT INTO `doumail` VALUES (45, 22, 21, '豆邮分页', '2019-05-16 12:30:56', 0, 0);
INSERT INTO `doumail` VALUES (46, 22, 21, '豆邮分页', '2019-05-16 12:30:58', 0, 0);
INSERT INTO `doumail` VALUES (47, 22, 21, '豆邮分页', '2019-05-16 12:31:01', 0, 0);
INSERT INTO `doumail` VALUES (48, 22, 21, '豆邮分页', '2019-05-16 12:31:03', 0, 0);
INSERT INTO `doumail` VALUES (49, 22, 21, '豆邮分页', '2019-05-16 12:31:06', 0, 0);
INSERT INTO `doumail` VALUES (50, 22, 21, '豆邮分页', '2019-05-16 12:31:08', 0, 0);
INSERT INTO `doumail` VALUES (51, 22, 21, '豆邮分页', '2019-05-16 12:31:11', 0, 0);
INSERT INTO `doumail` VALUES (52, 22, 21, '豆邮分页11', '2019-05-16 12:31:15', 0, 0);
INSERT INTO `doumail` VALUES (53, 22, 21, '222', '2019-05-16 12:31:20', 0, 0);
INSERT INTO `doumail` VALUES (54, 22, 21, '3333', '2019-05-16 12:31:23', 0, 0);
INSERT INTO `doumail` VALUES (55, 22, 21, '4444', '2019-05-16 12:31:25', 0, 0);
INSERT INTO `doumail` VALUES (56, 22, 21, '&lt;script&gt;&lt;/script&gt;', '2019-05-16 12:36:04', 0, 0);
INSERT INTO `doumail` VALUES (57, 22, 21, '最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最大长度的限制最', '2019-05-16 12:37:51', 0, 0);
INSERT INTO `doumail` VALUES (58, 56, 21, 'DOUYOU', '2019-05-19 10:18:27', 0, 0);
INSERT INTO `doumail` VALUES (59, 56, 21, 'DOUYOUFSDFASDF', '2019-05-19 10:18:37', 0, 0);
INSERT INTO `doumail` VALUES (60, 56, 21, 'SDFASDFASDFA', '2019-05-19 10:18:41', 0, 0);
INSERT INTO `doumail` VALUES (61, 56, 21, 'SADFASDFASDFASDF', '2019-05-19 10:18:46', 0, 0);
INSERT INTO `doumail` VALUES (62, 56, 21, '&lt;script&gt;&lt;/script&gt;', '2019-05-19 10:19:28', 0, 0);
INSERT INTO `doumail` VALUES (63, 1, 21, '&lt;div id=&quot;container&quot; class=&quot;article-main-content&quot; style=&quot;font-size : 14px;&quot;&gt;\r\n											      			不想写文字，敷衍。。。。。。。。。。。。。。。。。&lt;div&gt;&lt;br&gt;&lt;/div&gt;&lt;div&gt;&lt;br&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;&lt;/div&gt;&lt;div id=&quot;editor-img-insert&quot; style=&quot;display: table;margin: 15px auto;&quot;&gt;&lt;img src=&quot; http://localhost:8080/DouBan/image/201905110121347139.jpg&quot; style=&quot;max-width:590px;&quot;&gt;&lt;/div&gt;&lt;br&gt;&lt;br&gt;\r\n											      		&lt;/div&gt;', '2019-05-19 23:56:19', 0, 0);
INSERT INTO `doumail` VALUES (64, 1, 1, '你是说', '2019-06-19 17:04:06', 0, 0);
INSERT INTO `doumail` VALUES (65, 1, 1, '我不想看见迷', '2019-06-19 17:04:17', 0, 0);
INSERT INTO `doumail` VALUES (66, 1, 1, '开行', '2019-06-19 17:04:26', 0, 0);
INSERT INTO `doumail` VALUES (67, 1, 1, 'CRIPT\r\n', '2019-06-19 17:04:36', 0, 0);
INSERT INTO `doumail` VALUES (68, 1, 21, '你是谁', '2019-06-19 20:38:30', 0, 0);
INSERT INTO `doumail` VALUES (69, 1, 21, '11100001010101232132', '2019-08-06 17:07:31', 0, 0);
INSERT INTO `doumail` VALUES (70, 1, 21, '121313213213213', '2019-08-06 17:07:52', 0, 0);
INSERT INTO `doumail` VALUES (71, 1, 21, '\r\n33333', '2019-08-06 17:07:58', 0, 0);
INSERT INTO `doumail` VALUES (72, 1, 21, '33333333...', '2019-08-06 17:08:04', 0, 0);
INSERT INTO `doumail` VALUES (73, 1, 1, 'sdfsdf', '2019-09-18 21:36:46', 0, 0);
INSERT INTO `doumail` VALUES (74, 1, 1, 'fsdfsdf', '2019-09-18 21:36:49', 0, 0);
INSERT INTO `doumail` VALUES (75, 59, 1, '你是谁\r\n', '2019-09-25 15:30:47', 0, 0);
INSERT INTO `doumail` VALUES (76, 1, 59, '我是1', '2019-09-25 15:31:25', 0, 0);
INSERT INTO `doumail` VALUES (77, 1, 59, 'sdf', '2019-10-01 16:23:52', 0, 0);
INSERT INTO `doumail` VALUES (78, 1, 64, '啦啦啦啦', '2019-12-08 14:22:49', 0, 0);
INSERT INTO `doumail` VALUES (79, 1, 64, '抓到了', '2019-12-08 14:24:54', 0, 0);
INSERT INTO `doumail` VALUES (80, 1, 1, 'diid', '2020-08-05 14:05:26', 0, 0);

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
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (141, 1, 21, 3, 1);
INSERT INTO `friend` VALUES (142, 1, 23, 2, 2);
INSERT INTO `friend` VALUES (143, 1, 1, 1, 1);
INSERT INTO `friend` VALUES (144, 53, 1, 1, 1);
INSERT INTO `friend` VALUES (146, 22, 1, 3, 1);
INSERT INTO `friend` VALUES (147, 56, 1, 3, 1);
INSERT INTO `friend` VALUES (149, 1, 22, 2, 2);
INSERT INTO `friend` VALUES (150, 1, 24, 1, 1);
INSERT INTO `friend` VALUES (151, 1, 26, 1, 1);
INSERT INTO `friend` VALUES (152, 1, 25, 1, 1);
INSERT INTO `friend` VALUES (155, 23, 1, 2, 2);
INSERT INTO `friend` VALUES (156, 22, 1, 2, 2);
INSERT INTO `friend` VALUES (157, 62, 1, 1, 1);
INSERT INTO `friend` VALUES (158, 62, 21, 3, 1);
INSERT INTO `friend` VALUES (159, 59, 1, 1, 1);
INSERT INTO `friend` VALUES (160, 1, 64, 1, 1);

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
-- Records of friend_group
-- ----------------------------
INSERT INTO `friend_group` VALUES (1, '无分组');
INSERT INTO `friend_group` VALUES (2, '分组1');

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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-04-19 02:23:08', 'http://47.102.212.18/iDouBan/image/202008011407305141.jpg', '个性签名', '', '小茗', '', '2019-04-19 21:13:48', NULL, NULL);
INSERT INTO `user` VALUES (21, '1', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-04-22 21:13:51', 'http://img.linzworld.cn/img/20200801140603.jpg', '2', '我是自我介绍', '小橙', NULL, '2019-04-22 21:13:51', NULL, NULL);
INSERT INTO `user` VALUES (22, '1234', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-04-23 00:52:54', 'http://img.linzworld.cn/img/20200801140603.jpg', '3', '我是自我介绍', '我是昵称3', '', '2019-04-23 00:52:54', NULL, NULL);
INSERT INTO `user` VALUES (23, '12345', '827CCB0EEA8A706C4C34A16891F84E7B', 0, 0, '2019-04-23 21:00:23', 'http://img.linzworld.cn/img/20200801140603.jpg', '4', '我是自我介绍', '我是昵称4', NULL, '2019-04-23 21:00:23', NULL, NULL);
INSERT INTO `user` VALUES (24, '123123', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-04-23 21:12:22', 'http://img.linzworld.cn/img/20200801140603.jpg', '5', '我是自我介绍', '我是昵称5', '', '2019-04-23 21:12:22', NULL, NULL);
INSERT INTO `user` VALUES (25, '12', 'C20AD4D76FE97759AA27A0C99BFF6710', 0, 0, '2019-04-25 03:36:09', 'http://img.linzworld.cn/img/20200801140603.jpg', '6', '我是自我介绍', '我是昵称6', '', '2019-04-25 03:36:09', NULL, NULL);
INSERT INTO `user` VALUES (26, '12312312312', '246CE4E0A58E035F231DAB824C10E0B1', 0, 0, '2019-04-25 20:25:00', 'http://img.linzworld.cn/img/20200801140603.jpg', '7', '我是自我介绍', '我是昵称7', NULL, '2019-04-25 20:25:00', NULL, NULL);
INSERT INTO `user` VALUES (27, '23', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-04-25 20:44:10', 'http://img.linzworld.cn/img/20200801140603.jpg', '8', '我是自我介绍', '我是昵称8', NULL, '2019-04-25 20:44:10', NULL, NULL);
INSERT INTO `user` VALUES (28, '4321', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-04-25 20:47:41', 'http://img.linzworld.cn/img/20200801140603.jpg', '9', '我是自我介绍', '我是昵称9', NULL, '2019-04-25 20:47:41', NULL, NULL);
INSERT INTO `user` VALUES (29, '111', '698D51A19D8A121CE581499D7B701668', 0, 0, '2019-04-25 20:56:01', 'http://img.linzworld.cn/img/20200801140603.jpg', '10', '我是自我介绍', '我是昵称10', NULL, '2019-04-25 20:56:01', NULL, NULL);
INSERT INTO `user` VALUES (30, '123412432', 'BC36348B5F1D07E96AC06095F1ED91E2', 0, 0, '2019-04-25 21:00:42', 'http://img.linzworld.cn/img/20200801140603.jpg', '11', '我是自我介绍', '我是昵称11', NULL, '2019-04-25 21:00:42', NULL, NULL);
INSERT INTO `user` VALUES (31, '1234323 ', '8D4646EB2D7067126EB08ADB0672F7BB', 0, 0, '2019-04-25 21:01:45', 'http://img.linzworld.cn/img/20200801140603.jpg', '12', '我是自我介绍', '我是昵称12', NULL, '2019-04-25 21:01:45', NULL, NULL);
INSERT INTO `user` VALUES (32, '1438884318@qq.com', '698D51A19D8A121CE581499D7B701668', 0, 0, '2019-04-25 21:13:18', 'http://img.linzworld.cn/img/20200801140603.jpg', '13', '我是自我介绍', '我是昵称13', NULL, '2019-04-25 21:13:18', NULL, NULL);
INSERT INTO `user` VALUES (33, '1438884318@163.com', '698D51A19D8A121CE581499D7B701668', 0, 0, '2019-04-25 21:13:47', 'http://img.linzworld.cn/img/20200801140603.jpg', '14', '我是自我介绍', '我是昵称14', NULL, '2019-04-25 21:13:47', NULL, NULL);
INSERT INTO `user` VALUES (34, '1234578@qq.com', 'D41D8CD98F00B204E9800998ECF8427E', 0, 0, '2019-04-25 21:16:11', 'http://img.linzworld.cn/img/20200801140603.jpg', '15', '我是自我介绍', '我是昵称15', NULL, '2019-04-25 21:16:11', NULL, NULL);
INSERT INTO `user` VALUES (35, '11@qq.com', 'D41D8CD98F00B204E9800998ECF8427E', 0, 0, '2019-04-25 22:11:47', 'http://img.linzworld.cn/img/20200801140603.jpg', '16', '我是自我介绍', '我是昵称16', NULL, '2019-04-25 22:11:47', NULL, NULL);
INSERT INTO `user` VALUES (44, '12322222', '6512BD43D9CAA6E02C990B0A82652DCA', 0, 0, '2019-04-30 23:16:52', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, '我是自我介绍', '我是昵称17', NULL, '2019-04-30 23:16:52', NULL, NULL);
INSERT INTO `user` VALUES (45, '123456', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 0, '2019-04-30 23:31:48', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, '我是自我介绍', '我是昵称18', NULL, '2019-04-30 23:31:48', NULL, NULL);
INSERT INTO `user` VALUES (46, '222@qq.com', 'D41D8CD98F00B204E9800998ECF8427E', 0, 0, '2019-05-05 21:29:38', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, '我是自我介绍', '我是昵称19', NULL, '2019-05-05 21:29:38', NULL, NULL);
INSERT INTO `user` VALUES (47, '111111', '6512BD43D9CAA6E02C990B0A82652DCA', 0, 0, '2019-05-05 22:27:19', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, '我是自我介绍', '我是昵称20', NULL, '2019-05-05 22:27:19', NULL, NULL);
INSERT INTO `user` VALUES (48, '123@qq.com', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-05-05 22:28:20', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, '我是自我介绍', '我是昵称21', NULL, '2019-05-05 22:28:20', NULL, NULL);
INSERT INTO `user` VALUES (49, '2', 'C81E728D9D4C2F636F067F89CC14862C', 0, 0, '2019-05-12 07:21:59', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, '我是自我介绍', '大黄', NULL, '2019-05-12 07:21:59', NULL, NULL);
INSERT INTO `user` VALUES (50, '3', 'ECCBC87E4B5CE2FE28308FD9F2A7BAF3', 0, 0, '2019-05-13 03:08:05', 'http://img.linzworld.cn/img/20200801140603.jpg', '', '', '小绿', '', '2019-05-13 03:08:05', NULL, NULL);
INSERT INTO `user` VALUES (51, '1234444', '7B787F439CCCA19FC34208EE618A294E', 0, 0, '2019-05-15 20:19:59', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-05-15 20:19:59', NULL, NULL);
INSERT INTO `user` VALUES (52, '1233122312@qq.com', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-05-15 20:25:08', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-05-15 20:25:08', NULL, NULL);
INSERT INTO `user` VALUES (53, '32', '6364D3F0F495B6AB9DCF8D3B5C6E0B01', 0, 0, '2019-05-15 20:30:52', 'http://img.linzworld.cn/img/20200801140603.jpg', 'aaaa', '32', '我是', '24534', '2019-05-15 20:30:52', NULL, NULL);
INSERT INTO `user` VALUES (55, '12344', 'C4CA4238A0B923820DCC509A6F75849B', 0, 0, '2019-05-15 20:57:24', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-05-15 20:57:24', NULL, NULL);
INSERT INTO `user` VALUES (56, '2222@qq.com', '67D0D20751175881BD2F8ECE381B5DFD', 0, 0, '2019-05-19 10:00:35', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-05-19 10:00:35', NULL, NULL);
INSERT INTO `user` VALUES (57, '11', '202CB962AC59075B964B07152D234B70', 0, 0, '2019-09-24 19:45:15', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-09-24 19:45:15', NULL, NULL);
INSERT INTO `user` VALUES (58, '1234@qq.com', '46F94C8DE14FB36680850768FF1B7F2A', 0, 0, '2019-09-24 21:51:18', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-09-24 21:51:18', NULL, NULL);
INSERT INTO `user` VALUES (59, '1111@qq.com', '46F94C8DE14FB36680850768FF1B7F2A', 0, 0, '2019-09-24 21:56:49', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-09-24 21:56:49', NULL, NULL);
INSERT INTO `user` VALUES (60, '12345@qq.com', '46F94C8DE14FB36680850768FF1B7F2A', 0, 0, '2019-09-24 21:57:50', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-09-24 21:57:50', NULL, NULL);
INSERT INTO `user` VALUES (61, '123@163.com', '46F94C8DE14FB36680850768FF1B7F2A', 0, 0, '2019-09-24 22:00:17', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-09-24 22:00:17', NULL, NULL);
INSERT INTO `user` VALUES (62, '19@qq.com', '46F94C8DE14FB36680850768FF1B7F2A', 0, 0, '2019-09-24 22:25:28', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-09-24 22:25:28', NULL, NULL);
INSERT INTO `user` VALUES (63, '2461122700@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 0, '2019-12-08 14:09:43', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某', NULL, '2019-12-08 14:09:43', NULL, NULL);
INSERT INTO `user` VALUES (64, '3351262025@qq.com', '012DB0D9A29E55D418D97566A90E64D2', 0, 0, '2019-12-08 14:13:52', 'http://img.linzworld.cn/img/20200801140603.jpg', NULL, NULL, '某某1', NULL, '2019-12-08 14:13:52', NULL, NULL);
INSERT INTO `user` VALUES (65, '724957988@qq.com', 'C51CD8E64B0AEB778364765013DF9EBE', 0, 0, '2020-09-12 20:42:47', 'http://47.102.212.18/iDouBan/image/202009122043373564.', NULL, NULL, '某某', NULL, '2020-09-12 20:42:47', NULL, NULL);
INSERT INTO `user` VALUES (66, '1219913130@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', 0, 0, '2020-09-28 15:32:15', 'http://47.102.212.18/DouBan/image/default.png', NULL, NULL, '某某', NULL, '2020-09-28 15:32:15', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
