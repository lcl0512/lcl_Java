/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : class_test

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 29/09/2020 09:50:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受捐单位',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_linkman` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (5, '上海拉扎斯信息科技有限公司', '上海市普陀区真北路788号520室', '王磊', '18966758767', '2020-09-15 23:42:50');
INSERT INTO `company_info` VALUES (23, '阿里巴巴（中国）网络技术有限公司', '浙江省杭州市滨江区网商路699号', '戴珊', '18678778989', '2020-09-16 22:35:25');
INSERT INTO `company_info` VALUES (24, '字节跳动有限公司', '北京市海淀区北三环西路43号院2号楼5层503室  ', '张利东', '16787675665', '2020-09-23 14:34:44');
INSERT INTO `company_info` VALUES (25, '华为技术有限公司', '深圳市龙岗区坂田华为总部办公楼 ', '赵明路', '15789886677', '2020-09-23 14:35:54');
INSERT INTO `company_info` VALUES (26, '广州网易计算机系统有限公司', '广州市天河区思蕴路5号自编A2栋2楼01单元  ', '丁磊', '18767564354', '2020-09-23 14:46:55');

-- ----------------------------
-- Table structure for donation_info
-- ----------------------------
DROP TABLE IF EXISTS `donation_info`;
CREATE TABLE `donation_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `donate_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `project_id` int(11) NULL DEFAULT NULL,
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(2) NULL DEFAULT NULL,
  `donor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of donation_info
-- ----------------------------
INSERT INTO `donation_info` VALUES (3, '1310499152957816833', 7, 4, '希望工程', '希望工程', 1, '喵喵喵', 1, '2020-09-28 16:39:21');
INSERT INTO `donation_info` VALUES (4, '1310499648519073795', 7, 7, '靖西商城', '为对抗京东而成立', 0, '马云', 1, '2020-09-28 16:41:07');
INSERT INTO `donation_info` VALUES (28, '1310499648519073796', 8, 11, '春蕾计划', '如果知识不能改变命运，\n\n还有什么能改变命运呢？\n\n这是中国第一个专门资助女童入学的公益项目，1989年发起，已执行31年；\n\n也是迄今为止，全中国帮扶乡村女孩数量最大、最具影响力的公益项目，369万女童的命运，因此改变。\n\n这一个个女童绽放出的自信笑脸，和她们家庭命运的改变，是我们前行的动力。', 0, '赵日', 1, '2020-09-28 16:41:14');
INSERT INTO `donation_info` VALUES (29, '1310499648519073797', 8, 9, '星光童行陪伴计划', '小燕（化名）是四川眉山青神县高台镇黄莺岭村的一名失依儿童。父母早年离婚，母亲不再联系家里，全靠奶奶将小燕抚养长大，父亲因为身体原因一直在附近的县城餐馆做一些轻体力活，收入勉强够家里的基本开支。', 1, '灵台', 0, '2020-09-28 16:41:16');
INSERT INTO `donation_info` VALUES (30, '1310519169753563138', 8, 4, '希望工程', '希望工程1', 0, '爱丽丝', 0, '2020-09-28 17:58:13');

-- ----------------------------
-- Table structure for donation_project
-- ----------------------------
DROP TABLE IF EXISTS `donation_project`;
CREATE TABLE `donation_project`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NULL DEFAULT NULL COMMENT '捐献单位外键',
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名',
  `project_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目详情',
  `project_leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目负责人',
  `project_status` int(1) NULL DEFAULT NULL COMMENT '项目状态(0是筹款中，1是结束筹款）',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of donation_project
-- ----------------------------
INSERT INTO `donation_project` VALUES (4, 5, '希望工程', '希望工程1', '赵明路', 0, NULL, '2020-09-16 08:00:00', '2021-09-16 08:00:00');
INSERT INTO `donation_project` VALUES (7, 23, '靖西商城', '为对抗京东而成立', '戴珊', 0, NULL, '2020-09-16 08:00:00', '2021-09-16 08:00:00');
INSERT INTO `donation_project` VALUES (8, 23, '授渔计划', '2013年，由中国社会福利基金会与北京师范大学、新华网、中育集团和美年大健康发起“授渔计划”。\n\n    在中国社会福利基金会指导下，“授渔计划”相继和团中央“青年之声”服务办公室、中国青年网、中华职业教育社、公安部扶贫办、中国人民公安大学、民建中央办公厅、教育部教师工作司、清华大学及民政部对口帮扶县等达成公益合作，共同开展公益项目，通过“互联网+教育+公益”的平台，开展乡村学校“双师课堂”项目，资助家庭贫困学生，助力乡村教育均衡发展。\n\n', '阿尔法', 1, NULL, '2020-09-23 08:00:00', '2020-09-24 08:00:00');
INSERT INTO `donation_project` VALUES (9, 24, '星光童行陪伴计划', '小燕（化名）是四川眉山青神县高台镇黄莺岭村的一名失依儿童。父母早年离婚，母亲不再联系家里，全靠奶奶将小燕抚养长大，父亲因为身体原因一直在附近的县城餐馆做一些轻体力活，收入勉强够家里的基本开支。', '张利东', 0, NULL, '2020-09-23 08:00:00', '2020-09-30 08:00:00');
INSERT INTO `donation_project` VALUES (10, 25, '爱心包裹学生圆梦', '本项目是由中国扶贫基金会与腾讯公益慈善基金会共同发起的一项全民公益活动。以捐购爱心包裹的形式，关爱贫困地区及灾区小学生。所有通过月捐平台捐赠的善款将以“腾讯爱心网友”的名义结对关爱需要帮助的学生，100元捐购一个学生型文具包，帮助贫困小学生更好完成学业。', '赵明路', 0, NULL, '2020-09-23 08:00:00', '2021-09-23 08:00:00');
INSERT INTO `donation_project` VALUES (11, 5, '春蕾计划', '如果知识不能改变命运，\n\n还有什么能改变命运呢？\n\n这是中国第一个专门资助女童入学的公益项目，1989年发起，已执行31年；\n\n也是迄今为止，全中国帮扶乡村女孩数量最大、最具影响力的公益项目，369万女童的命运，因此改变。\n\n这一个个女童绽放出的自信笑脸，和她们家庭命运的改变，是我们前行的动力。', '赵明路', 0, NULL, '2020-09-23 08:00:00', '2020-10-10 08:00:00');

-- ----------------------------
-- Table structure for item_list
-- ----------------------------
DROP TABLE IF EXISTS `item_list`;
CREATE TABLE `item_list`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `donation_info_id` int(11) NULL DEFAULT NULL COMMENT '物品清单外键id',
  `currency` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币种',
  `amount` float(10, 2) NULL DEFAULT NULL COMMENT '捐献金额',
  `item_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '捐赠物名称',
  `item_amount` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '捐赠数量',
  `supplier` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商',
  `standard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产标准',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_list
-- ----------------------------
INSERT INTO `item_list` VALUES (11, 3, '人名币', 10000000.00, '', '', NULL, NULL, '2020-09-08 16:20:11');
INSERT INTO `item_list` VALUES (14, 4, NULL, NULL, '口罩', '10000000', '未知', 'fafoasfjalfjlasf', NULL);
INSERT INTO `item_list` VALUES (16, 28, NULL, NULL, '被子', '10000', '未知', '749231492-479234', NULL);
INSERT INTO `item_list` VALUES (17, 29, '美元', 100000.00, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `item_list` VALUES (18, 30, NULL, NULL, '衣服', '10000', '未知', '493247923f', NULL);
INSERT INTO `item_list` VALUES (19, 30, NULL, NULL, '教科书', '10000', '未知', 'alfjaofja', NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `notice_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (3, '2020中国互联网公益峰会“云端”举行', '14日，2020年中国互联网公益峰会首次在“云上”举行，3000余家公益机构和社会组织代表进行在线互动与“云端对话”。30余位国内外知名专家，社会企业家、媒体人和公益慈善组织从业人士从公益慈善数字化、透明化建设、生态化搭建等问题与在线参会的公益慈善机构从业者、网民进行了深入分享和探讨。', '2020-09-23 14:43:30');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` int(6) NULL DEFAULT NULL,
  `name` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 202001, '廖倡霖', 100);
INSERT INTO `student` VALUES (2, 202002, '郭婷婷', 111);
INSERT INTO `student` VALUES (3, 202003, '凌天', 99);
INSERT INTO `student` VALUES (4, 203004, '赵日天', 123);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'gtt', '123');
INSERT INTO `user` VALUES (2, 'lcl', '111');

-- ----------------------------
-- Table structure for user1
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '(1是管理员，0是普通用户）',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `age` int(3) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `local` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体地址',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`, `telephone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user1
-- ----------------------------
INSERT INTO `user1` VALUES (1, '郭婷婷', 'e10adc3949ba59abbe56e057f20f883e', 1, '18199987676', '男', 20, '江西', '南昌', '西湖区', '22333@qq.com', '2020-09-16 14:15:34');
INSERT INTO `user1` VALUES (7, '网易云', 'e10adc3949ba59abbe56e057f20f883e', 0, '13478978767', '1', 22, '河北', '石家庄', '未知', 'wy@xx.com', '2020-09-17 20:33:04');
INSERT INTO `user1` VALUES (8, '网抑云', '96e79218965eb72c92a549dd5a330112', 0, '13567458978', '1', 33, '天津', NULL, NULL, 'wangyiyun@163.com', '2020-09-16 14:47:27');
INSERT INTO `user1` VALUES (9, '廖倡霖', '96e79218965eb72c92a549dd5a330112', 1, '18170779832', '1', 21, '江西', NULL, NULL, 'lcl@163.com', '2020-09-16 14:56:52');

SET FOREIGN_KEY_CHECKS = 1;
