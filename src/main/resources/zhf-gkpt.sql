/*
Navicat MySQL Data Transfer

Source Server         : 172.16.2.11
Source Server Version : 50649
Source Host           : 172.16.2.11:3306
Source Database       : zhf-gkpt

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2021-07-16 18:06:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bi_alarm
-- ----------------------------
DROP TABLE IF EXISTS `bi_alarm`;
CREATE TABLE `bi_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '预警名称',
  `type` int(11) DEFAULT NULL COMMENT '预警类型id',
  `level` int(11) DEFAULT NULL COMMENT '预警级别id',
  `threshold` varchar(50) DEFAULT NULL COMMENT '预警阈值',
  `kpi` varchar(50) DEFAULT NULL COMMENT 'kpi',
  `default_handler` varchar(50) DEFAULT NULL COMMENT '预设处理人',
  `whether_set` int(11) DEFAULT NULL COMMENT '是否设置',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警阈值信息';

-- ----------------------------
-- Records of bi_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for bi_environment_info
-- ----------------------------
DROP TABLE IF EXISTS `bi_environment_info`;
CREATE TABLE `bi_environment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pm_two_five` decimal(5,1) DEFAULT NULL COMMENT 'PM2.5(ug)',
  `pm_ten` decimal(5,1) DEFAULT NULL COMMENT 'PM10(ug)',
  `wind_speed` decimal(5,1) DEFAULT NULL COMMENT '风速(level)',
  `noise` decimal(5,1) DEFAULT NULL COMMENT '噪音(dB)',
  `humidity` decimal(5,1) DEFAULT NULL COMMENT '湿度(%rh)',
  `temperature` decimal(5,1) DEFAULT NULL COMMENT '温度(℃)',
  `muck_protection_rate` decimal(5,1) DEFAULT NULL COMMENT '渣土防护率(%)',
  `topsoil_protection_rate` decimal(5,1) DEFAULT NULL COMMENT '表土保护率(%)',
  `environmental_rectification_rate` decimal(5,1) DEFAULT NULL COMMENT '环境整改率(%)',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='环境信息';

-- ----------------------------
-- Records of bi_environment_info
-- ----------------------------
INSERT INTO `bi_environment_info` VALUES ('1', '464.1', '5345.5', '6123.3', null, null, null, null, null, null, '0', null, '2021-07-15 17:40:27', null);
INSERT INTO `bi_environment_info` VALUES ('2', '77.8', '55.5', '469.0', '151.0', '369.0', '302.0', '438.0', '992.0', '88.0', '0', 'gvude5', '2021-07-13 17:44:26', '2021-07-13 17:45:24');
INSERT INTO `bi_environment_info` VALUES ('3', '809.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-15 16:21:13', null);
INSERT INTO `bi_environment_info` VALUES ('4', '809.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-15 16:22:04', null);

-- ----------------------------
-- Table structure for bi_message_info
-- ----------------------------
DROP TABLE IF EXISTS `bi_message_info`;
CREATE TABLE `bi_message_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `type` int(11) DEFAULT NULL COMMENT '消息类型id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息通知';

-- ----------------------------
-- Records of bi_message_info
-- ----------------------------

-- ----------------------------
-- Table structure for bi_safety_info
-- ----------------------------
DROP TABLE IF EXISTS `bi_safety_info`;
CREATE TABLE `bi_safety_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '安全整改编号',
  `type` int(11) DEFAULT NULL COMMENT '安全类型id',
  `check_type` int(11) DEFAULT NULL COMMENT '检查类型id',
  `check_company` varchar(255) DEFAULT NULL COMMENT '被检测单位',
  `project_name` varchar(255) DEFAULT NULL COMMENT '工程名称',
  `check_time` datetime DEFAULT NULL COMMENT '检查时间',
  `participants` varchar(255) DEFAULT NULL COMMENT '参加检测人员id',
  `request_time` datetime DEFAULT NULL COMMENT '要求时间',
  `check_record` varchar(2000) DEFAULT NULL COMMENT '检测记录（富文本）',
  `rectification_situation` int(11) DEFAULT NULL COMMENT '整改情况id',
  `complet_time` datetime DEFAULT NULL COMMENT '完成时间',
  `rectification_person_liable` int(11) DEFAULT NULL COMMENT '整改责任人id',
  `rectification_details` varchar(2000) DEFAULT NULL COMMENT '整改详情（富文本）',
  `whether_request` int(11) DEFAULT NULL COMMENT '是否符合要求id',
  `safety_officer_opinion` varchar(1000) DEFAULT NULL COMMENT '施工单位安全员意见',
  `project_manager_opinion` varchar(1000) DEFAULT NULL COMMENT '施工单位项目经理意见',
  `chief_supervisor_opinion` varchar(1000) DEFAULT NULL COMMENT '监理单位总监意见',
  `project_director_opinion` varchar(1000) DEFAULT NULL COMMENT '建设单位项目主管意见',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='安全整改信息';

-- ----------------------------
-- Records of bi_safety_info
-- ----------------------------
INSERT INTO `bi_safety_info` VALUES ('10', 'qweq', '7', '4', 'adsas', 'fdsf', '2021-07-16 09:36:13', '10,11', '2021-07-16 09:36:25', 'adadsaas', '9', '2021-07-16 09:36:35', '10', 'dasdasd', '11', 'adasdda', 'asdasdad', 'fsdfsdf', 'dasdasds', '0', null, null, null);
INSERT INTO `bi_safety_info` VALUES ('11', 'asdasd', '7', '4', 'sfdsd', 'sfdf', null, null, null, null, '9', null, null, null, '11', null, null, null, null, '0', null, null, null);
INSERT INTO `bi_safety_info` VALUES ('12', 'sdfsdf', '7', '5', null, null, null, null, null, null, '9', null, null, null, '11', null, null, null, null, '0', null, null, null);
INSERT INTO `bi_safety_info` VALUES ('13', 'asd', '8', '6', null, null, null, null, null, null, '10', null, null, null, '12', null, null, null, null, '0', null, null, null);

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `super_id` int(11) DEFAULT NULL COMMENT '上级部门id',
  `code` varchar(30) DEFAULT NULL COMMENT '部门编码',
  `whether_company` int(4) DEFAULT NULL COMMENT '是否是公司id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='部门信息';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '部门', '0', null, '12', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('2', '公司', '0', null, '12', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('10', '技术开发部', '1', 'werw', '12', '0', null, '2021-07-07 17:06:51', '2021-07-07 17:06:53');
INSERT INTO `sys_department` VALUES ('11', '人力资源部', '1', 'sd', '12', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('12', '市场部', '1', 'sfsd', '12', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('13', '中建八局第三建筑有限公司', '2', 'dsfs', '11', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('14', '成都苏宁工程咨询有限公司', '2', 'fsdfs', '11', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `value` varchar(50) DEFAULT NULL COMMENT '字典值',
  `type` varchar(20) DEFAULT NULL COMMENT '字典类型',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0正常，1弃用）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='字典值';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('1', 'Java开发', 'sys_user_position', '0', '0', null, '2021-07-09 10:16:45', null);
INSERT INTO `sys_dictionary` VALUES ('2', '前端开发', 'sys_user_position', '0', '0', null, '2021-07-09 10:16:49', null);
INSERT INTO `sys_dictionary` VALUES ('3', 'UI设计', 'sys_user_position', '0', '0', null, '2021-07-09 10:16:59', null);
INSERT INTO `sys_dictionary` VALUES ('4', '施工管理安全检查', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('5', '施工现场安全文明生产检查', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('6', '安全专项检查', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('7', '安全检查', 'bi_safety_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('8', '安全隐患', 'bi_safety_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('9', '已整改', 'bi_safety_situation', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('10', '未整改', 'bi_safety_situation', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('11', '是', 'sys_whether', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('12', '否', 'sys_whether', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_type`;
CREATE TABLE `sys_dictionary_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '字典类型名称',
  `type` varchar(20) DEFAULT NULL COMMENT '字典类型',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0正常，1弃用）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dictionary_type
-- ----------------------------
INSERT INTO `sys_dictionary_type` VALUES ('1', '用户岗位', 'sys_user_position', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('2', '安全整改检查类型', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('3', '安全整改安全类型', 'bi_safety_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('4', '安全整改整改情况', 'bi_safety_situation', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('5', '是否', 'sys_whether', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  `type` int(11) DEFAULT NULL COMMENT '日志类型',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志信息';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `permissions_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `parent_permissions` int(11) DEFAULT NULL COMMENT '父权限id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='权限信息';

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
INSERT INTO `sys_permissions` VALUES ('1', '基础模块', '0', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('2', '环境信息', '1', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('3', '查看环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('4', '新增环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('5', '修改环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('6', '删除环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('7', '安全信息整改', '1', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('8', '查看安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('9', '新增安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('10', '修改安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('11', '删除安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('12', '用户信息', '17', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('13', '查看用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('14', '新增用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('15', '修改用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('16', '删除用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('17', '系统模块', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '超级管理员', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('11', '环境信息管理员', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('12', '环境信息用户', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('13', '环境信息观察者', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('14', '安全整改管理员', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permissions`;
CREATE TABLE `sys_role_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permissions_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='角色权限信息';

-- ----------------------------
-- Records of sys_role_permissions
-- ----------------------------
INSERT INTO `sys_role_permissions` VALUES ('1', '10', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('2', '10', '4', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('3', '10', '5', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('4', '10', '6', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('5', '11', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('6', '11', '4', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('7', '11', '5', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('8', '11', '6', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('9', '12', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('10', '12', '4', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('11', '12', '5', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('12', '13', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('13', '10', '8', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('14', '10', '9', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('15', '10', '10', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('16', '10', '11', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('17', '14', '8', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('18', '14', '9', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('19', '14', '10', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('20', '14', '11', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('21', '10', '13', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('22', '10', '14', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('23', '10', '15', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('24', '10', '16', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') DEFAULT NULL COMMENT '性别（枚举型，1为男，2为女）',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `card_number` varchar(30) DEFAULT NULL COMMENT '卡号',
  `department` int(11) DEFAULT NULL COMMENT '部门id',
  `position` int(11) DEFAULT NULL COMMENT '岗位id',
  `work_type` int(11) DEFAULT NULL COMMENT '工种',
  `job_code` varchar(50) DEFAULT NULL COMMENT '职业代码',
  `job_level` int(11) DEFAULT NULL COMMENT '职业等级',
  `helmet_type` int(11) DEFAULT NULL COMMENT '安全帽等级',
  `bound_helmet` varchar(50) DEFAULT NULL COMMENT '绑定安全帽',
  `user_number` varchar(50) DEFAULT NULL COMMENT '人员编号',
  `whether_screen` int(11) DEFAULT NULL COMMENT '是否进入大屏id',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `id_number` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_contact_phone` varchar(20) DEFAULT NULL COMMENT '紧急联系人电话',
  `face_picture` varchar(50) DEFAULT NULL COMMENT '脸图文件名',
  `account_expiration_date` datetime DEFAULT NULL COMMENT '账号到期时间',
  `log_status` tinyint(4) DEFAULT NULL COMMENT '登录状态（0未登录，1已登录，2锁定）',
  `log_last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `log_error_number` tinyint(4) DEFAULT NULL COMMENT '登录错误次数',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '插入时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_name_password` (`user_name`,`password`) USING BTREE COMMENT '登录索引'
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='人员信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10', 'julyyasuo', '79f88134f817f20172476f5ecabc16d0', '超级管理员', '男', '22', '777', '10', '1', null, null, null, null, null, null, null, '18200178194', null, '510811199510170899', '1995-10-17 00:00:00', null, '18200178194', 'julyyasuo.jpg', null, '0', '2021-07-15 16:38:33', '0', '0', 'admin', '2021-07-07 16:15:18', '2021-07-07 16:15:12');
INSERT INTO `sys_user` VALUES ('11', 'julyriven', '79f88134f817f20172476f5ecabc16d0', '环境信息管理员', '女', '26', '888', '10', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2021-07-15 15:08:43', '0', '0', null, null, '2021-07-15 16:16:42');
INSERT INTO `sys_user` VALUES ('12', 'julyzed', '79f88134f817f20172476f5ecabc16d0', '环境信息用户', '男', '25', '999', '11', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2021-07-15 15:08:46', '0', '0', null, null, null);
INSERT INTO `sys_user` VALUES ('13', 'julyleesin', '79f88134f817f20172476f5ecabc16d0', '观察者', '男', '25', '666', '12', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '2021-07-15 15:08:49', '0', '0', null, null, null);
INSERT INTO `sys_user` VALUES ('14', '修杰.龙', '79f88134f817f20172476f5ecabc16d0', '安全整改管理员', '女', '62', 'x5jsxl', '13', '1', null, null, null, null, null, null, null, 'yznn1a', null, '5h068i', '2021-07-08 08:00:00', 'fer6yp', '17832745794', '2u4q75', null, '0', '2021-07-08 08:00:00', '0', '0', '8c0vp6', '2021-07-08 17:47:59', '2021-07-08 17:50:10');
INSERT INTO `sys_user` VALUES ('15', '奥数第4', '79f88134f817f20172476f5ecabc16d0', '环境安全管理员', '女', '30', 'dj9p2n', '13', '57', null, null, null, null, null, null, null, 'cowqvu', '鑫磊.曹@yahoo.com', '2nd141', '2021-07-14 08:00:00', 'ow1hoo', '15130934036', 'dobpga', null, '1', '2021-07-12 08:00:00', '0', '0', 'v82q8a', '2021-07-12 13:39:42', null);
INSERT INTO `sys_user` VALUES ('16', '奥数第', '79f88134f817f20172476f5ecabc16d0', '伟祺.江', '女', '30', 'dj9p2n', '13', '57', null, null, null, null, null, null, null, 'cowqvu', '鑫磊.曹@yahoo.com', '2nd141', '2021-07-14 08:00:00', 'ow1hoo', '15130934036', 'dobpga', null, '2', '2021-07-12 08:00:00', '5', '0', 'v82q8a', '2021-07-12 13:39:42', null);
INSERT INTO `sys_user` VALUES ('17', '瑞霖.叶', '79f88134f817f20172476f5ecabc16d0', '瑞霖.叶', '男', '64', 'hurnlt', '14', '587', null, null, null, null, null, null, null, 'i22t1u', '荣轩.罗@yahoo.com', '2dzlv3', '2021-07-13 08:00:00', '8klhw1', '17052490900', 'p12etj', null, null, '2021-07-13 08:00:00', '0', '0', 'f4u8v1', '2021-07-13 16:28:00', null);
INSERT INTO `sys_user` VALUES ('18', 'wwwww', '79f88134f817f20172476f5ecabc16d0', '伟祺.江', '女', '30', 'dj9p2n', '14', '57', null, null, null, null, null, null, null, 'cowqvu', '鑫磊.曹@yahoo.com', '2nd141', '2021-07-14 08:00:00', 'ow1hoo', '15130934036', 'dobpga', null, null, '2021-07-15 16:19:43', '0', '0', 'v82q8a', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '人员ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT '' COMMENT '注备',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='人员角色信息';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '10', '10', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('2', '11', '11', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('3', '12', '12', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('4', '13', '13', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('5', '14', '14', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('6', '15', '11', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('7', '15', '14', '0', '', null, null);
